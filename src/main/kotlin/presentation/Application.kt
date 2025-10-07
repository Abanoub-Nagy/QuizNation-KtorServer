package com.example.presentation

import com.example.presentation.config.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    configureKoin()
    configSerialization()
    configureStatusPages()
    configureRouting()
    configureLogging()
    configureValidation()
}