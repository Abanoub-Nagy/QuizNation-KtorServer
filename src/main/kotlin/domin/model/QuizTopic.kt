package com.example.domin.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizTopic(
    val id: String? = null,
    val name: String,
    val code: Int,
    val imageUrl: String
)
