package com.ramaa.pmobile_uas.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class StockResponse(

	@field:SerializedName("data")
	val data: DataStock? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Company(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null
) : Parcelable

@Entity
@Parcelize
data class ResultsStockItem(

	@field:SerializedName("symbol")
	@PrimaryKey val symbol: String,

	@field:SerializedName("change")
	val change: Int? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("close")
	val close: Int? = null,

	@field:SerializedName("percent")
	val percent: Double? = null,

) : Parcelable

@Parcelize
data class DataStock(

	@field:SerializedName("results")
	val results: List<ResultsStockItem?>? = null
) : Parcelable
