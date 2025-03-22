package com.example.presentaion.routes.quiz_question

import com.example.presentaion.config.quizQuestions
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions() {
    get("/quiz/questions") {
        val quizTopicCode = call.queryParameters["quizTopicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val filteredQuestions = if (quizTopicCode != null) {
            quizQuestions
                .filter { it.quizTopicCode == quizTopicCode }
                .take(limit ?: quizQuestions.size)
        }else{
            quizQuestions
                .take(limit ?: quizQuestions.size)
        }

        if (filteredQuestions.isNotEmpty()){
            call.respond(message = filteredQuestions, status = HttpStatusCode.OK)
        }else{
            call.respond(message = "No Questions found", status = HttpStatusCode.NotFound)
        }
        call.respond(filteredQuestions)
    }
}