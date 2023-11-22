package com.ramaa.narutowiki.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ramaa.narutowiki.domain.model.ItemCharacter

@Parcelize
data class CharactersResponse(

	@field:SerializedName("characters")
	val itemCharacters: List<ItemCharacter>,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("pageSize")
	val pageSize: Int,

	@field:SerializedName("currentPage")
	val currentPage: Int
) : Parcelable