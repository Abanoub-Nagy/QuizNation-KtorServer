package com.example.presentaion.routes.quiz_question

import com.example.domin.model.QuizQuestion
import com.example.presentaion.config.quizQuestions
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestion() {
    post("/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        quizQuestions.add(question)
        call.respond(message = "Question added successfully", status = HttpStatusCode.Created)
    }
}