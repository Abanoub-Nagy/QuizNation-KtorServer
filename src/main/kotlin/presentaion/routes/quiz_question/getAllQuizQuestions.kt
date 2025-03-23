package com.example.presentaion.routes.quiz_question

import com.example.domin.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get("/quiz/questions") {
        val quizTopicCode = call.queryParameters["quizTopicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val questions = repository.getAllQuizQuestions(quizTopicCode, limit)
        if (questions.isNotEmpty()) {
            call.respond(message = questions, status = HttpStatusCode.OK)
        } else {
            call.respond(message = "No Questions found", status = HttpStatusCode.NotFound)
        }
        call.respond(questions)
    }
}