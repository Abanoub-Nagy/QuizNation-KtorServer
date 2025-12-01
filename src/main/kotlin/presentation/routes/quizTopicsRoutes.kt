package com.example.presentation.routes

import com.example.domain.model.QuizTopic
import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.routes.path.QuizTopicRoutesPath
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.quizTopicsRoutes(
    repository: QuizTopicRepository
) {
    post<QuizTopicRoutesPath> {
        val quizTopic = call.receive<QuizTopic>()
        repository.upsertQuizTopic(quizTopic).onSuccess {
            call.respond(
                message = "Quiz topic added", status = HttpStatusCode.Created
            )
        }.onFailure { error ->
            respondWithError(error = error)
        }
    }

    get<QuizTopicRoutesPath> {
        repository.getAllQuizTopics().onSuccess { topics ->
            call.respond(
                message = topics, status = HttpStatusCode.OK
            )

        }.onFailure { error ->
            respondWithError(error = error)
        }
    }

    get<QuizTopicRoutesPath.ById> { path ->
        repository.getQuizTopicById(path.topicId).onSuccess { quizTopic ->
            call.respond(
                message = quizTopic, status = HttpStatusCode.OK
            )
        }.onFailure { error ->
            respondWithError(error)
        }
    }

    delete<QuizTopicRoutesPath.ById> { path ->
        repository.deleteQuizTopicById(path.topicId).onSuccess {
            call.respond(
                HttpStatusCode.NoContent
            )
        }.onFailure { error ->
            respondWithError(error)
        }
    }
}