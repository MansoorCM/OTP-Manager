package com.example.otpmanager.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContactDetailScreen(modifier: Modifier = Modifier) {
    Text(
        "Contact Detail", modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}