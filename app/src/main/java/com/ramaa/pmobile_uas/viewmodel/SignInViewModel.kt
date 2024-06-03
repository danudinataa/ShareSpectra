package com.ramaa.pmobile_uas.viewmodel

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ramaa.pmobile_uas.presentation.login.GoogleAuthUiClient
import com.ramaa.pmobile_uas.presentation.login.SignInResult
import com.ramaa.pmobile_uas.presentation.login.SignInState
import com.ramaa.pmobile_uas.presentation.login.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}
