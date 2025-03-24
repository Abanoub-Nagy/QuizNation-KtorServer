package com.example.presentaion.routes.quiz_topic

import com.example.domin.repository.QuizTopicRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.getAllQuizTopics(
    quizTopicRepository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath> {
        quizTopicRepository.getAllQuizTopics()
            .onSuccess { topics ->
                call.respond(
                    message = topics,
                    status = HttpStatusCode.OK
                )

            }
            .onFailure { error ->
                respondWithError(error = error)
            }
    }
}