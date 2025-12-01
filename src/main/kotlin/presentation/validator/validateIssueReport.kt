package com.example.presentation.validator

import com.example.domain.model.IssueReport
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateIssueReport() {
    validate<IssueReport> { issueReport ->
        val emailRegex = Regex(pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")

        when {
            issueReport.quizQuestionId.isBlank() -> {
                ValidationResult.Invalid(
                    "Question ID must not be blank"
                )
            }

            issueReport.issueType.isBlank() -> {
                ValidationResult.Invalid("Issue type must not be blank")
            }

            issueReport.timestamp.isBlank() -> {
                ValidationResult.Invalid("Timestamp must not be blank")
            }

            issueReport.additionalComment != null && issueReport.additionalComment.length < 5 -> {
                ValidationResult.Invalid("Additional comment must be at least 5 characters long")
            }

            issueReport.userEmail != null && !issueReport.userEmail.matches(emailRegex) -> {
                ValidationResult.Invalid(
                    "Invalid email format"
                )
            }

            else -> {
                ValidationResult.Valid
            }
        }
    }
}