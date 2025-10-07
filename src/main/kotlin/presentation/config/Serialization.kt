package com.example.presentation.config

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configSerialization() {
    install(ContentNegotiation) {
        json()
    }
}