package com.ramaa.pmobile_uas.util

sealed class UIComponent {

    data class Toast(val message: String): UIComponent()

}