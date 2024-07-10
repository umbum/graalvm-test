package dev.umbum.sample

import dev.umbum.external.currencybeacon.rest.CurrencyBeaconClient
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val currencyBeaconClient: CurrencyBeaconClient,
) {
    @GetMapping("/test1")
    fun test1(): String {
        logger.info("### call test1")
        return currencyBeaconClient.getLatest("USD", listOf("KRW")).date
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SampleController::class.java)
    }
}
