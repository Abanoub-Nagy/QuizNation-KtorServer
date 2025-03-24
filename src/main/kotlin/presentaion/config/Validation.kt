package com.example.presentaion.config

import com.example.presentaion.validator.validateQuizQuestion
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
    }
}