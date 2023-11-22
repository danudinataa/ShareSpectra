package com.ramaa.narutowiki.util

sealed class UIComponent {

    data class Toast(val message: String): UIComponent()

}