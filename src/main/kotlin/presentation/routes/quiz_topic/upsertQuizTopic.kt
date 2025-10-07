package com.example.presentation.routes.quiz_topic

import com.example.domain.model.QuizTopic
import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.upsertQuizTopic(
    repository: QuizTopicRepository
) {
    post<QuizTopicRoutesPath> {
        val quizTopic = call.receive<QuizTopic>()
        repository.upsertQuizTopic(quizTopic)
            .onSuccess {
                call.respond(
                    message = "Quiz topic added",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error = error)
            }
    }
}