package com.ramaa.narutowiki.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ramaa.narutowiki.domain.model.Character

@Parcelize
data class CharactersResponse(

	@field:SerializedName("characters")
	val characters: List<Character>,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("pageSize")
	val pageSize: Int,

	@field:SerializedName("currentPage")
	val currentPage: Int
) : Parcelable