package com.example.presentaion.config

import com.example.presentaion.validator.validateIssueReport
import com.example.presentaion.validator.validateQuizQuestion
import com.example.presentaion.validator.validateQuizTopic
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
        validateQuizTopic()
        validateIssueReport()
    }
}