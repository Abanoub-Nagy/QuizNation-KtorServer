package com.example.presentation.validator

import com.example.domain.model.QuizQuestion
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateQuizQuestion() {
    validate<QuizQuestion> { quizQuestion ->
        when {
            quizQuestion.question.isBlank() || quizQuestion.question.length < 5 -> {
                ValidationResult.Invalid("Question must be at least 5 characters long and not be blank")
            }

            quizQuestion.correctAnswer.isBlank() -> {
                ValidationResult.Invalid("Correct answer must not be blank")
            }

            quizQuestion.incorrectAnswers.isEmpty() -> {
                ValidationResult.Invalid("There must be at least one incorrect answer")
            }

            quizQuestion.explanation.isBlank() -> {
                ValidationResult.Invalid("Explanation must not be blank")
            }

            quizQuestion.quizTopicCode <= 0 -> {
                ValidationResult.Invalid("Quiz topic code must be greater than 0")
            }

            else -> {
                ValidationResult.Valid
            }
        }
    }
}