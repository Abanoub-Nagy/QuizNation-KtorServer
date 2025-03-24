package com.example.data.repository

import com.example.data.database.entity.QuizQuestionEntity
import com.example.data.mapper.toQuizQuestion
import com.example.data.mapper.toQuizQuestionEntity
import com.example.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.example.domin.model.QuizQuestion
import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.util.DataError
import com.example.domin.util.Result
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
    ): Result<List<QuizQuestion>, DataError> {
        return try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = quizTopicCode?.let {
                Filters.eq(
                    QuizQuestionEntity::quizTopicCode.name, it
                )
            } ?: Filters.empty()

            val questions = questionCollection
                .find(filter = filterQuery)
                .limit(questionLimit)
                .map { it.toQuizQuestion() }
                .toList()

            if (questions.isNotEmpty()) {
                Result.Success(questions)
            } else {
                Result.Failure(DataError.NotFound)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getQuizQuestionById(
        id: String?
    ): Result<QuizQuestion, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val questionEntity = questionCollection
                .find(filter = filterQuery)
                .firstOrNull()

            if (questionEntity != null) {
                val question = questionEntity.toQuizQuestion()
                Result.Success(question)
            } else {
                Result.Failure(DataError.NotFound)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteQuizQuestionById(
        id: String?
    ): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val deleteResult = questionCollection.deleteOne(filter = filterQuery)
            if (deleteResult.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun upsertQuizQuestion(
        quizQuestion: QuizQuestion
    ): Result<Unit, DataError> {
        return try {
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
                val updateResult = questionCollection.updateOne(filter = filterQuery, update = updateQuery)
                if (updateResult.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            return Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}