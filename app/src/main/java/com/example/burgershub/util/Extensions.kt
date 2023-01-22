package com.example.burgershub.util

import com.google.gson.Gson
import retrofit2.HttpException
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

inline fun <reified T> HttpException.getErrorResponse(): T? {
    return try {
        Gson().fromJson(response()?.errorBody()?.string(), T::class.java)
    } catch (e: Exception) {
        null
    }
}

fun Float.formattedValue(): String? {
    val nf: NumberFormat = DecimalFormat(
        "#,##0.00", DecimalFormatSymbols(
            Locale("pt", "BR")
        )
    )
    return nf.format(this)
}