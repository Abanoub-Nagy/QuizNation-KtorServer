package com.example.presentation.config

import com.example.presentation.validator.validateIssueReport
import com.example.presentation.validator.validateQuizQuestion
import com.example.presentation.validator.validateQuizTopic
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
        validateQuizTopic()
        validateIssueReport()
    }
}