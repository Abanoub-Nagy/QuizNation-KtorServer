package com.example.presentaion.routes.quiz_topic

import com.example.domin.repository.QuizTopicRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.deleteQuizTopicById(
    topicRepository: QuizTopicRepository
) {
    delete<QuizTopicRoutesPath.ById> { path ->
        topicRepository.deleteQuizTopicById(path.topicId)
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