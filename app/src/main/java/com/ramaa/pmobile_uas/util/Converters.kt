package com.ramaa.pmobile_uas.util

// Converters.kt
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramaa.pmobile_uas.data.remote.response.Company

class Converters {
    @TypeConverter
    fun fromCompany(company: Company?): String? {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun toCompany(companyString: String?): Company? {
        return Gson().fromJson(companyString, object : TypeToken<Company>() {}.type)
    }
}