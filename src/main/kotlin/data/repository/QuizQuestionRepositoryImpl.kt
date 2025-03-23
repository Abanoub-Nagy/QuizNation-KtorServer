package com.example.data.repository

import com.example.data.database.entity.QuizQuestionEntity
import com.example.data.mapper.toQuizQuestion
import com.example.data.mapper.toQuizQuestionEntity
import com.example.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.example.domin.model.QuizQuestion
import com.example.domin.repository.QuizQuestionRepository
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizQuestionRepository {

    private val questionCollection = mongoDatabase.getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)

    override suspend fun getAllQuizQuestions(
        quizTopicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {
        return try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = quizTopicCode?.let {
                Filters.eq(
                    QuizQuestionEntity::quizTopicCode.name, it
                )
            } ?: Filters.empty()
            questionCollection
                .find(filter = filterQuery)
                .limit(questionLimit)
                .map { it.toQuizQuestion() }
                .toList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getQuizQuestionById(id: String): QuizQuestion? {
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val questionEntity = questionCollection
                .find(filter = filterQuery)
                .firstOrNull()
            questionEntity?.toQuizQuestion()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deleteQuizQuestionById(id: String): Boolean {
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val deleteResult = questionCollection.deleteOne(filter = filterQuery)
            deleteResult.deletedCount > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun upsertQuizQuestion(quizQuestion: QuizQuestion) {
        try {
            if (quizQuestion.id == null) {
                questionCollection.insertOne(quizQuestion.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq(
                    QuizQuestionEntity::_id.name, quizQuestion.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, quizQuestion.question),
                    Updates.set(QuizQuestionEntity::correctAnswer.name, quizQuestion.correctAnswer),
                    Updates.set(QuizQuestionEntity::incorrectAnswers.name, quizQuestion.incorrectAnswers),
                    Updates.set(QuizQuestionEntity::explanation.name, quizQuestion.explanation),
                    Updates.set(QuizQuestionEntity::quizTopicCode.name, quizQuestion.quizTopicCode),
                )
                questionCollection.updateOne(filter = filterQuery, update = updateQuery)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}