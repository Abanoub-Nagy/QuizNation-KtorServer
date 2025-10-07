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


fun Route.insertQuizQuestionInBulk(
    repository: QuizQuestionRepository
) {
    post<QuizQuestionRoutesPath.Bulk> {
        val questions = call.receive<List<QuizQuestion>>()
        repository.insertQuizQuestionInBulk(questions).onSuccess {
            call.respond(
                message = "Questions added successfully", status = HttpStatusCode.Created
            )
        }.onFailure { error ->
            respondWithError(error)
        }
    }
}