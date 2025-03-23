package com.example.data.repository

import com.example.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.example.domin.model.QuizQuestion
import com.example.domin.repository.QuizQuestionRepository
import com.mongodb.kotlin.client.coroutine.MongoDatabase

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizQuestionRepository {

    private val questionCollection = mongoDatabase.getCollection<QuizQuestion>(QUESTIONS_COLLECTION_NAME)

    private val quizQuestions = mutableListOf<QuizQuestion>()

    override suspend fun getAllQuizQuestions(quizTopicCode: Int?, limit: Int?): List<QuizQuestion> {
        return if (quizTopicCode != null) {
            quizQuestions
                .filter { it.quizTopicCode == quizTopicCode }
                .take(limit ?: quizQuestions.size)
        } else {
            quizQuestions
                .take(limit ?: quizQuestions.size)
        }
    }

    override suspend fun getQuizQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override suspend fun upsertQuizQuestion(quizQuestion: QuizQuestion) {
        questionCollection.insertOne(quizQuestion)
    }

    override suspend fun deleteQuizQuestionById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }
}