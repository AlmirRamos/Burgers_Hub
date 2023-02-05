package com.example.burgershub.util

import com.google.gson.Gson
import retrofit2.HttpException
import java.text.NumberFormat
import java.util.*

inline fun <reified T> HttpException.getErrorResponse(): T? {
    return try {
        Gson().fromJson(response()?.errorBody()?.string(), T::class.java)
    } catch (e: Exception) {
        null
    }
}

fun Float?.formattedValue(removeSymbol: Boolean = false): String {
    val moneyFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    moneyFormatter.currency = Currency.getInstance("BRL")

    val value = if (this == null) {
        moneyFormatter.format(0f)
    } else {
        moneyFormatter.format(this)
    }

    return if (removeSymbol) value.replace("R\$ ", "") else value
}