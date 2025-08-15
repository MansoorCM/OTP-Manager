package com.example.otpmanager.ui

import androidx.annotation.StringRes

sealed class UiEvent {
    data class ShowSnackBar(@StringRes val message: Int) : UiEvent()
    object NavigateBack : UiEvent()
}
