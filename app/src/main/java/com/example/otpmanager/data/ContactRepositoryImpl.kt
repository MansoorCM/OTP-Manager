package com.example.otpmanager.data

import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(private val contactDao: ContactDao) : ContactRepository {
    override fun getAllContacts(): Flow<List<Contact>> = contactDao.getAll()

    override suspend fun insertContact(contact: Contact) {
        contactDao.insert(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.delete(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        contactDao.update(contact)
    }
}