package com.example.domin.repository

import com.example.domin.model.QuizQuestion

interface QuizQuestionRepository {
    suspend fun getAllQuizQuestions(quizTopicCode: Int?, limit: Int?): List<QuizQuestion>
    suspend fun getQuizQuestionById(id: String): QuizQuestion?
    suspend fun upsertQuizQuestion(quizQuestion: QuizQuestion)
    suspend fun deleteQuizQuestionById(id: String): Boolean
}