package com.example.presentaion.routes.quiz_question

import com.example.domin.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestionById(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if(id.isNullOrBlank()){
            call.respond(message = "Invalid question id", status = HttpStatusCode.BadRequest)
            return@get
        }
        val question = repository.getQuizQuestionById(id)
        if (question != null) {
            call.respond(message = question, status = HttpStatusCode.OK)
        }else{
            call.respond(message = "Question not found", status = HttpStatusCode.NotFound)
        }
    }
}