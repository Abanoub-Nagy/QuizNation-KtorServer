package com.example.presentaion.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root() {
    get("/") {
        call.respondText("welcome to the quiz api")
    }
}