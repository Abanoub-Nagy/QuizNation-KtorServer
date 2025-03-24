package com.example.presentaion.routes.quiz_question

import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get<QuizQuestionRoutesPath>(){ path ->
        repository.getAllQuizQuestions(path.quizTopicCode, path.limit)
            .onSuccess { questions ->
                call.respond(
                    message = questions,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

}