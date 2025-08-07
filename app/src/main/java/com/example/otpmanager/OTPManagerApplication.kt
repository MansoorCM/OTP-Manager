package com.example.otpmanager

import android.app.Application
import com.example.otpmanager.data.ContactDao
import com.example.otpmanager.data.ContactDatabase
import com.example.otpmanager.data.ContactRepository
import com.example.otpmanager.data.ContactRepositoryImpl

class OTPManagerApplication : Application() {
    private val contactDao: ContactDao by lazy {
        ContactDatabase.getDatabase(this).contactDao()
    }
    val contactRepository: ContactRepository by lazy {
        ContactRepositoryImpl(contactDao)
    }
}