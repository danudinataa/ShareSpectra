package com.ramaa.pmobile_uas.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CompanyResponse(

	@field:SerializedName("data")
	val data: ResultsCompanies? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class ResultsCompanies(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("sub_sector_name")
	val subSectorName: String? = null,

	@field:SerializedName("about")
	val about: String? = null,

	@field:SerializedName("ipo_founders_shares")
	val ipoFoundersShares: Long? = null,

	@field:SerializedName("ipo_fund_raised")
	val ipoFundRaised: Long? = null,

	@field:SerializedName("ipo_securities_administration_bureau")
	val ipoSecuritiesAdministrationBureau: String? = null,

	@field:SerializedName("ipo_listing_date")
	val ipoListingDate: String? = null,

	@field:SerializedName("ipo_total_listed_shares")
	val ipoTotalListedShares: Long? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("fax")
	val fax: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("ipo_offering_shares")
	val ipoOfferingShares: Int? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("industry_name")
	val industryName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("npwp")
	val npwp: String? = null,

	@field:SerializedName("ipo_percentage")
	val ipoPercentage: Double? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("sub_industry_name")
	val subIndustryName: String? = null,

	@field:SerializedName("sector_name")
	val sectorName: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
