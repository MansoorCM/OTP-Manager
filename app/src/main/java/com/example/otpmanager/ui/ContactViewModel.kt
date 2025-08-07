package com.example.otpmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otpmanager.data.ContactRepository
import com.example.otpmanager.data.ContactUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepository: ContactRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState: StateFlow<ContactUiState>
        get() = _uiState

    init {
        loadData()
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
}