package com.example.domain.repository

import com.example.domain.model.QuizQuestion
import com.example.domain.util.DataError
import com.example.domain.util.Result

interface QuizQuestionRepository {

    suspend fun getAllQuizQuestions(
        quizTopicCode: Int?
    ): Result<List<QuizQuestion>, DataError>


    suspend fun getRandomQuizQuestions(
        quizTopicCode: Int?, limit: Int?
    ): Result<List<QuizQuestion>, DataError>

    suspend fun upsertQuizQuestion(
        quizQuestion: QuizQuestion
    ): Result<Unit, DataError>

    suspend fun insertQuizQuestionInBulk(questions: List<QuizQuestion>): Result<Unit, DataError>

    suspend fun getQuizQuestionById(
        id: String?
    ): Result<QuizQuestion, DataError>

    suspend fun deleteQuizQuestionById(
        id: String?
    ): Result<Unit, DataError>
}