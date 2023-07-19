package com.example.dinergate.network

const val API_KEY = "baf926709d928da3"

fun stateToQuery(
    lat: Double,
    lng: Double,
    range: Int,
    order: Int = 4
): String {
    return "?key=${API_KEY}&lat=${lat}&lng=${lng}&range=${range}&order=${order}&format=json"
}