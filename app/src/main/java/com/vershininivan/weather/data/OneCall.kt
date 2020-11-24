package com.vershininivan.weather.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class OneCall(
    val current: Current,
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
) {
    class Deserializer: ResponseDeserializable<OneCall> {
        override fun deserialize(content: String): OneCall? = Gson().fromJson(content, OneCall::class.java)
    }
}