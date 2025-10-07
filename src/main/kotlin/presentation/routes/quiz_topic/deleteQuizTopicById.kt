package com.example.presentation.routes.quiz_topic

import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizTopicById(
    repository: QuizTopicRepository
) {
    delete<QuizTopicRoutesPath.ById> { path ->
        repository.deleteQuizTopicById(path.topicId)
            .onSuccess {
                call.respond(
                    HttpStatusCode.NoContent
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}