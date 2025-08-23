package com.example.otpmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otpmanager.OTPManagerApplication
import com.example.otpmanager.R
import com.example.otpmanager.data.Contact
import com.example.otpmanager.data.ContactRepository
import com.example.otpmanager.data.ContactUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepository: ContactRepository) : ViewModel() {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as OTPManagerApplication
                ContactViewModel(application.contactRepository)
            }
        }
    }
}