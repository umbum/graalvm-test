package dev.umbum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableScheduling
class GraalvmTestApplication

fun main(args: Array<String>) {
    runApplication<GraalvmTestApplication>(*args)
}
