package com.example.presentaion.routes.quiz_question

import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
) {
    delete(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        repository.deleteQuizQuestionById(id)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}