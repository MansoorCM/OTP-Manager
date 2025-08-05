package com.example.otpmanager.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactDaoTest {

    private lateinit var database: ContactDatabase
    private lateinit var dao: ContactDao

    private val contact1 = Contact(1, "Alice", "Johnson", "9876543210")
    private val contact2 = Contact(2, "Bob", "Smith", "9123456780")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.contactDao()
    }

    @Test
    fun contactDb_InsertOneItemToDb() = runBlocking {
        dao.insert(contact1)
        val res = dao.getAll().first()
        assertEquals(contact1, res[0])
    }

    @Test
    fun contactDb_UpdateItem() = runBlocking {
        dao.insert(contact1)
        val updatedContact1 = Contact(1, "Carol", "Williams", "9988776655")
        dao.update(updatedContact1)
        val res = dao.getAll().first()
        assertEquals(updatedContact1, res[0])
    }

    @After
    fun closeDb() {
        database.close()
    }

}