package com.example.presentation.routes

import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.routes.path.QuizQuestionRoutesPath
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.quizQuestionsRoutes(
    repository: QuizQuestionRepository
) {
    delete<QuizQuestionRoutesPath.ById> { path ->
        repository.deleteQuizQuestionById(path.questionId).onSuccess {
            call.respond(HttpStatusCode.NoContent)
        }.onFailure { error ->
            respondWithError(error)
        }
    }

    get<QuizQuestionRoutesPath>() { path ->
        repository.getAllQuizQuestions(path.quizTopicCode).onSuccess { questions ->
            call.respond(
                message = questions, status = HttpStatusCode.OK
            )
        }.onFailure { error ->
            respondWithError(error)
        }
    }

    get<QuizQuestionRoutesPath.ById> { path ->
        repository.getQuizQuestionById(path.questionId).onSuccess { question ->
            call.respond(message = question, status = HttpStatusCode.OK)
        }.onFailure { error ->
            respondWithError(error)
        }
    }

    get<QuizQuestionRoutesPath.Random>() { path ->
        repository.getRandomQuizQuestions(path.quizTopicCode, path.limit).onSuccess { questions ->
            call.respond(
                message = questions, status = HttpStatusCode.OK
            )
        }.onFailure { error ->
            respondWithError(error)
        }
    }

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

    post<QuizQuestionRoutesPath> {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuizQuestion(question).onSuccess {
            call.respond(
                message = "Question added successfully", status = HttpStatusCode.Created
            )
        }.onFailure { error ->
            respondWithError(error)
        }
    }
}