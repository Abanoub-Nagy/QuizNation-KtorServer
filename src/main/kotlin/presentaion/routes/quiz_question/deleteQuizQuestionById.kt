package com.example.presentaion.routes.quiz_question

import com.example.domin.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
) {
    delete(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if(id.isNullOrBlank()){
            call.respond(message = "Invalid question id", status = HttpStatusCode.BadRequest)
            return@delete
        }
        val isDeleted = repository.deleteQuizQuestionById(id)
        if (isDeleted){
            call.respond(HttpStatusCode.NoContent)
        }else{
            call.respond(message = "No Question to delete", status = HttpStatusCode.NotFound)
        }
    }
}