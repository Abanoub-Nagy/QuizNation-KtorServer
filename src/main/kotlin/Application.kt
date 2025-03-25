package com.example

import com.example.presentaion.config.*
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
