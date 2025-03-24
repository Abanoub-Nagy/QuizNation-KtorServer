package com.example.domin.repository

import com.example.domin.model.QuizQuestion
import com.example.domin.util.DataError
import com.example.domin.util.Result

interface QuizQuestionRepository {

    suspend fun getAllQuizQuestions(
        quizTopicCode: Int?,
        limit: Int?
    ): Result<List<QuizQuestion>, DataError>

    suspend fun upsertQuizQuestion(
        quizQuestion: QuizQuestion
    ): Result<Unit, DataError>

    suspend fun getQuizQuestionById(
        id: String?
    ): Result<QuizQuestion, DataError>

    suspend fun deleteQuizQuestionById(
        id: String?
    ): Result<Unit, DataError>
}