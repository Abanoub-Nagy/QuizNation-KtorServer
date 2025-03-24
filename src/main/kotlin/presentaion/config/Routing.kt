package com.example.presentaion.config

import com.example.data.database.DatabaseFactory
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.data.repository.QuizTopicRepositoryImpl
import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.repository.QuizTopicRepository
import com.example.presentaion.routes.quiz_question.deleteQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestions
import com.example.presentaion.routes.quiz_question.upsertQuizQuestion
import com.example.presentaion.routes.quiz_topic.deleteQuizTopicById
import com.example.presentaion.routes.quiz_topic.getAllQuizTopics
import com.example.presentaion.routes.quiz_topic.getQuizTopicById
import com.example.presentaion.routes.quiz_topic.upsertQuizTopic
import com.example.presentaion.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    install(Resources)

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)
    val quizTopicRepository: QuizTopicRepository = QuizTopicRepositoryImpl(mongoDatabase)
    routing {
        root()

        //Quiz Question
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        getAllQuizQuestionById(quizQuestionRepository)

        //Quiz Topic
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )
    }
}

