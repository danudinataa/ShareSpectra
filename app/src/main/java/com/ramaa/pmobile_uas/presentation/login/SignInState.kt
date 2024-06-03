package com.ramaa.pmobile_uas.presentation.login

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)