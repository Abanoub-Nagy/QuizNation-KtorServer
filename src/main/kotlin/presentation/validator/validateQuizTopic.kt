package com.example.presentation.validator

import com.example.domain.model.QuizTopic
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateQuizTopic() {
    validate<QuizTopic> { quizTopic ->
        when {
            quizTopic.name.isBlank() || quizTopic.name.length < 3 -> {
                ValidationResult.Invalid(
                    reason = "Topic name must be at least 3 characters long and not empty"
                )
            }

            quizTopic.imageUrl.isBlank() -> {
                ValidationResult.Invalid(
                    reason = "Image url must not be empty"
                )
            }

            quizTopic.code < 0 -> {
                ValidationResult.Invalid(
                    reason = "Topic code must be a whole number"
                )
            }

            else -> {
                ValidationResult.Valid
            }
        }
    }
}