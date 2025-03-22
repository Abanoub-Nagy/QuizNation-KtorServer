 package com.example

import com.example.presentaion.config.configSerialization
import com.example.presentaion.config.configureLogging
import com.example.presentaion.config.configureRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

 fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    configSerialization()
    configureRouting()
    configureLogging()
}
