package com.example.presentaion.config

import com.example.domin.model.QuizQuestion
import com.example.presentaion.routes.quiz_question.deleteQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestions
import com.example.presentaion.routes.quiz_question.upsertQuizQuestion
import com.example.presentaion.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()

        getAllQuizQuestions()
        upsertQuizQuestion()
        deleteQuizQuestionById()
        getAllQuizQuestionById()
    }
}

val quizQuestions = mutableListOf<QuizQuestion>()