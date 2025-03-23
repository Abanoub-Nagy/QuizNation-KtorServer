package com.example.domin.repository

import com.example.domin.model.QuizQuestion

interface QuizQuestionRepository {
    fun getAllQuizQuestions(quizTopicCode: Int?, limit: Int?): List<QuizQuestion>
    fun getQuizQuestionById(id: String): QuizQuestion?
    fun upsertQuizQuestion(quizQuestion: QuizQuestion)
    fun deleteQuizQuestionById(id: String): Boolean
}