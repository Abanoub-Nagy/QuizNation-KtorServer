package com.example.presentaion.config

import com.example.data.database.DatabaseFactory
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.domin.repository.QuizQuestionRepository
import com.example.presentaion.routes.quiz_question.deleteQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestions
import com.example.presentaion.routes.quiz_question.upsertQuizQuestion
import com.example.presentaion.routes.root
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    install(Resources)

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)

    routing {
        root()

        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        getAllQuizQuestionById(quizQuestionRepository)
    }
}

