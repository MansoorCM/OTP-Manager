package com.example.otpmanager.data

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getAllContacts(): Flow<List<Contact>>
    suspend fun insertContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    suspend fun updateContact(contact: Contact)
}