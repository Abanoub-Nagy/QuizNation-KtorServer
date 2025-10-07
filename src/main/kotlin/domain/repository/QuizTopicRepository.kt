package com.example.domain.repository

import com.example.domain.model.QuizTopic
import com.example.domain.util.DataError
import com.example.domain.util.Result


interface QuizTopicRepository {
    suspend fun getAllQuizTopics(): Result<List<QuizTopic>, DataError>
    suspend fun upsertQuizTopic(topic: QuizTopic): Result<Unit, DataError>
    suspend fun getQuizTopicById(id: String?): Result<QuizTopic, DataError>
    suspend fun deleteQuizTopicById(id: String?): Result<Unit, DataError>
}