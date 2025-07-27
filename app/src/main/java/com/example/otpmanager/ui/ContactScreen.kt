package com.example.otpmanager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.otpmanager.data.Contact

@Composable
fun ContactScreen(modifier: Modifier = Modifier) {

}

@Composable
fun ContactItem(contact: Contact, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(Icons.Default.AccountCircle, "", modifier = Modifier.padding(8.dp))
            Text("${contact.firstName} ${contact.lastName}", modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    ContactItem(Contact(2, "Bob", "Smith", "+1-202-555-0175"))
}