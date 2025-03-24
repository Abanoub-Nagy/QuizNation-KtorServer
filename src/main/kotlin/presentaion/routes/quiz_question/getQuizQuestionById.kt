package com.example.presentaion.routes.quiz_question

import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestionById(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        repository.getQuizQuestionById(id)
            .onSuccess { question ->
                call.respond(message = question, status = HttpStatusCode.OK)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}