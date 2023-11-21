package com.ramaa.narutowiki.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(

    @field:SerializedName("images")
    val images: List<String>,

    @field:SerializedName("jutsu")
    val jutsu: List<String>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("natureType")
    val natureType: List<String?>?,

) : Parcelable