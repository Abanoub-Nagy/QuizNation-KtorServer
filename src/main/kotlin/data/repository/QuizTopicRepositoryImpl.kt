package com.example.data.repository

import com.example.data.database.entity.QuizTopicEntity
import com.example.data.mapper.toQuizTopic
import com.example.data.mapper.toQuizTopicEntity
import com.example.data.util.Constant.TOPIC_COLLECTION_NAME
import com.example.domin.model.QuizTopic
import com.example.domin.repository.QuizTopicRepository
import com.example.domin.util.DataError
import com.example.domin.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizTopicRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizTopicRepository {

    private val topicsCollection = mongoDatabase
        .getCollection<QuizTopicEntity>(TOPIC_COLLECTION_NAME)

    override suspend fun getAllQuizTopics(): Result<List<QuizTopic>, DataError> {
        return try {
            val sortQuery = Sorts.ascending(
                QuizTopicEntity::code.name
            )
            val topics = topicsCollection
                .find()
                .sort(sortQuery)
                .map { it.toQuizTopic() }
                .toList()
            if (topics.isNotEmpty()) {
                Result.Success(topics)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun upsertQuizTopic(topic: QuizTopic): Result<Unit, DataError> {
        return try {
            if (topic.id == null) {
                topicsCollection.insertOne(
                    topic.toQuizTopicEntity()
                )
            } else {
                val filterQuery = Filters.eq(
                    QuizTopicEntity::_id.name, topic.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizTopicEntity::name.name, topic.name),
                    Updates.set(QuizTopicEntity::code.name, topic.code),
                    Updates.set(QuizTopicEntity::imageUrl.name, topic.imageUrl)
                )
                val updateResult = topicsCollection.updateOne(
                    filterQuery, updateQuery
                )
                if (updateResult.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getQuizTopicById(id: String?): Result<QuizTopic, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizTopicEntity::_id.name, id
            )
            val topicEntity = topicsCollection
                .find(filterQuery)
                .firstOrNull()

            if (topicEntity != null) {
                Result.Success(topicEntity.toQuizTopic())
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteQuizTopicById(id: String?): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizTopicEntity::_id.name, id
            )
            val deleteResult = topicsCollection.deleteOne(
                filterQuery
            )

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
}