package com.example.presentaion.routes.quiz_question

import com.example.domin.model.QuizQuestion
import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository
) {
    post("/quiz/questions") {
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