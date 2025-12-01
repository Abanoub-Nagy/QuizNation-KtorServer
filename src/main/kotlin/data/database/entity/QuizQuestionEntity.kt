package com.example.data.database.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class QuizQuestionEntity(
    @BsonId
    val _id: String = ObjectId().toString(),
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val quizTopicCode: Int,
    val explanation: String
)
