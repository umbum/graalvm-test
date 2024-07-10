package dev.umbum.external.util

import java.io.File
import java.io.FileInputStream
import java.util.Properties

object QueryStringConverter {
    fun convert(map: Map<String, String>): String {
        return map.entries.joinToString("&") { "${it.key}=${it.value}" }
    }

    fun convert(vararg pair: Pair<String, String>): String {
        return convert(pair.associate { it.first to it.second })
    }
}

fun getSecretResourceFromLocal(fileName: String): Properties {
    val resourceStream = FileInputStream("${System.getProperty("user.home")}${File.separator}secrets${File.separator}$fileName")
    val properties = Properties().apply { load(resourceStream) }
    resourceStream.close()

    return properties
}
