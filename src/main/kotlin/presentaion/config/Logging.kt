package com.example.presentaion.config

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import org.slf4j.event.Level

fun Application.configureLogging() {
    install(CallLogging){
        level = Level.DEBUG
    }
}