package com.example.presentation.routes.quiz_question

import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*

import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route


fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository
) {
    post<QuizQuestionRoutesPath> {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuizQuestion(question)
            .onSuccess {
                call.respond(
                    message = "Question added successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}