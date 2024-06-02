package com.ramaa.pmobile_uas.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ItemCharacter(

    @field:SerializedName("images")
    val images: List<String>?,

    @field:SerializedName("jutsu")
    val jutsu: List<String>?,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    @PrimaryKey val id: Int,

    @field:SerializedName("natureType")
    val natureType: List<String>?,

    @field:SerializedName("tools")
    val tools: List<String>?

) : Parcelable