package dev.umbum.external.currencybeacon.rest

import dev.umbum.external.currencybeacon.dto.CurrencyBeaconResponse
import dev.umbum.external.util.QueryStringConverter
import dev.umbum.external.util.getSecretResourceFromLocal
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class CurrencyBeaconClient(
    webClientBuilder: WebClient.Builder,
) {
    private val apiKey =
        getSecretResourceFromLocal("currency_beacon_api_key.properties")
            .getProperty("api_key")

    private val webClient =
        webClientBuilder
            .baseUrl("https://api.currencybeacon.com/")
            .build()

    fun getLatest(
        base: String,
        symbols: List<String>,
    ): CurrencyBeaconResponse {
        val queryString =
            QueryStringConverter.convert(
                "base" to base,
                "symbols" to symbols.joinToString(separator = ","),
                "api_key" to apiKey,
            )

        return webClient.get()
            .uri("/v1/latest?$queryString")
            .retrieve()
            .bodyToMono(CurrencyBeaconResponse::class.java)
            .block()!!
    }
}
