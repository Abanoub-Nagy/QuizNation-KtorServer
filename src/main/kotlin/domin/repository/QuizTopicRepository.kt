package com.example.domin.repository

import com.example.domin.model.QuizTopic
import com.example.domin.util.DataError
import com.example.domin.util.Result


interface QuizTopicRepository {
    suspend fun getAllQuizTopics(): Result<List<QuizTopic>, DataError>
    suspend fun upsertQuizTopic(topic: QuizTopic): Result<Unit, DataError>
    suspend fun getQuizTopicById(id: String?): Result<QuizTopic, DataError>
    suspend fun deleteQuizTopicById(id: String?): Result<Unit, DataError>
}