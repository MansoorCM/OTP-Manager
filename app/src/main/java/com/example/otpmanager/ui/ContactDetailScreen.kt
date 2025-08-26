package com.example.otpmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.otpmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    onBackClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    contactId: Int,
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = viewModel()
) {
    LaunchedEffect(contactId) {
        viewModel.getContactById(contactId)
    }
    val uiState by viewModel.uiState.collectAsState()
    val contact = uiState.contact

    Scaffold(topBar = {
        TopAppBar(
            {},
            navigationIcon = {
                IconButton(onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        ""
                    )
                }
            },
            actions = {
                IconButton({ onEditClick(contactId) }) {
                    Icon(Icons.Default.Edit, "")
                }
            })
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize()
                .padding(it)
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
            Button({
                viewModel.deleteContact(contact)
                onBackClick()
            }) {
                Text(stringResource(R.string.delete_contact))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactDetailScreenPreview() {
    ContactDetailScreen({}, {}, 0)
}