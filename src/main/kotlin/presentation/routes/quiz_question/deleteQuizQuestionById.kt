package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
) {
    delete<QuizQuestionRoutesPath.ById> { path ->
        repository.deleteQuizQuestionById(path.questionId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}