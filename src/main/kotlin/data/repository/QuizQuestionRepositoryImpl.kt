package com.example.data.repository

import com.example.domin.model.QuizQuestion
import com.example.domin.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl : QuizQuestionRepository {

    private val quizQuestions = mutableListOf<QuizQuestion>()
    override fun getAllQuizQuestions(quizTopicCode: Int?, limit: Int?): List<QuizQuestion> {
        return if (quizTopicCode != null) {
            quizQuestions
                .filter { it.quizTopicCode == quizTopicCode }
                .take(limit ?: quizQuestions.size)
        } else {
            quizQuestions
                .take(limit ?: quizQuestions.size)
        }
    }

    override fun getQuizQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override fun upsertQuizQuestion(quizQuestion: QuizQuestion) {
        quizQuestions.add(quizQuestion)
    }

    override fun deleteQuizQuestionById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }
}