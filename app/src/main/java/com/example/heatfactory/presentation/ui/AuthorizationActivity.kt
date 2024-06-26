package com.example.heatfactory.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.authorization.EnteringCode

class AuthorizationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val showSuccessDialog = remember { mutableStateOf(false) }
            val isVerificationSuccess = remember { mutableStateOf(false) }

            EnteringCode()

        }
    }
}

