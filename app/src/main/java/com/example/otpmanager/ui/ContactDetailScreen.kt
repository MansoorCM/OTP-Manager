package com.example.otpmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.otpmanager.R
import com.example.otpmanager.data.Contact

@Composable
fun ContactDetailScreen(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Text(
            "${contact.firstName} ${contact.lastName}",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(contact.phoneNum, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Button({}) {
            Text(stringResource(R.string.send_otp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button({}) {
            Text(stringResource(R.string.delete_contact))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactDetailScreenPreview() {
    ContactDetailScreen(Contact(1, "Alice", "Johnson", "9876543210"))
}