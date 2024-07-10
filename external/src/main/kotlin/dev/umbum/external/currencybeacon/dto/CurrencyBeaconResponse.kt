package dev.umbum.external.currencybeacon.dto

import java.math.BigDecimal

data class CurrencyBeaconResponse(
    val meta: Meta,
    val response: Response,
    val date: String,
    val base: String,
    val rates: Map<String, BigDecimal>,
) {
    data class Meta(
        val code: Int,
        val disclaimer: String,
    )

    data class Response(
        val date: String,
        val base: String,
        val rates: Map<String, BigDecimal>,
    )
}
