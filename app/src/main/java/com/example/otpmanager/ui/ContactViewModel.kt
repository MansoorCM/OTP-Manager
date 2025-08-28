package com.example.otpmanager.ui

import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otpmanager.BuildConfig
import com.example.otpmanager.OTPManagerApplication
import com.example.otpmanager.R
import com.example.otpmanager.data.Contact
import com.example.otpmanager.data.ContactRepository
import com.example.otpmanager.data.ContactUiState
import com.example.otpmanager.network.TwilioApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel(private val contactRepository: ContactRepository) : ViewModel() {

    private val accountSID = BuildConfig.TWILIO_ACCOUNT_SID
    private val authToken = BuildConfig.TWILIO_AUTH_TOKEN
    private val twilioPhone = BuildConfig.TWILIO_PHONE

    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState: StateFlow<ContactUiState>
        get() = _uiState

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadData()
    }

    private fun insertContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.insertContact(contact)
        }
    }

    fun onSaveClicked(contact: Contact) {
        if (isContactValid(contact)) {
            insertContact(contact)
            _uiEvent.trySend(UiEvent.NavigateBack)
        } else {
            _uiEvent.trySend(UiEvent.ShowSnackBar(R.string.contact_is_invalid))
        }
    }

    private fun isContactValid(contact: Contact): Boolean {
        if (contact.firstName.isEmpty()) {
            return false
        } else if (contact.lastName.isEmpty()) {
            return false
        }
        return contact.phoneNum.length == 10
    }

    private fun loadData() {
        viewModelScope.launch {
            contactRepository.getAllContacts().collect { contacts ->
                _uiState.update { currentState ->
                    currentState.copy(contacts = contacts)
                }
            }
        }
    }

    fun getContactById(id: Int) {
        viewModelScope.launch {
            contactRepository.getContactById(id).collect { contact ->
                if (contact == null) {
                    _uiState.update { currentState ->
                        currentState.copy(contact = Contact(0, "", "", ""))
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(contact = contact)
                    }
                }
            }
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.deleteContact(contact)
        }
    }

    fun onUpdateClicked(contact: Contact) {
        if (isContactValid(contact)) {
            updateContact(contact)
            _uiEvent.trySend(UiEvent.NavigateBack)
        } else {
            _uiEvent.trySend(UiEvent.ShowSnackBar(R.string.contact_is_invalid))
        }
    }

    private fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.updateContact(contact)
        }
    }

    fun sendMessage(otp: Int) {
        val formattedPhoneNum = "+91${_uiState.value.contact.phoneNum}"
        val body = "Hi, Your OTP is $otp"
        val base64EncodedCredentials = getEncodedCredentials(accountSID, authToken)
        val data = getSMSData(twilioPhone, formattedPhoneNum, body)

        TwilioApi.retrofitService.sendMessage(accountSID, base64EncodedCredentials, data)
            .enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    successfulResponse(response)
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    failureResponse()
                }
            })
    }

    private fun getEncodedCredentials(accountSID: String, authToken: String) =
        "Basic " + Base64.encodeToString(
            ("$accountSID:$authToken").toByteArray(), Base64.NO_WRAP
        )

    private fun getSMSData(from: String, phoneNum: String, body: String): HashMap<String, String> {
        val data: HashMap<String, String> = HashMap()
        data["From"] = from
        data["To"] = phoneNum
        data["Body"] = body
        return data
    }

    private fun successfulResponse(response: Response<ResponseBody?>) {
        if (response.isSuccessful) {
            _uiEvent.trySend(UiEvent.ShowSnackBar(R.string.sms_success))
        } else {
            _uiEvent.trySend(UiEvent.ShowSnackBar(R.string.sms_failure))
        }
    }

    private fun failureResponse() {
        _uiEvent.trySend(UiEvent.ShowSnackBar(R.string.sms_failure_internet))
    }

    // generates a six digit random number.
    fun getOTP(): Int {
        val start = 100000
        val end = 999999
        return (start..end).random()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as OTPManagerApplication
                ContactViewModel(application.contactRepository)
            }
        }
    }
}