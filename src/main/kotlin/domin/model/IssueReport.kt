package com.example.domin.model

import kotlinx.serialization.Serializable

@Serializable
data class IssueReport(
    val id: String? = null,
    val quizQuestionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timestamp: String
)
