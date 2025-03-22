package com.example.presentaion.routes.quiz_question

import com.example.domin.model.QuizQuestion
import com.example.presentaion.config.quizQuestions
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestionById() {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if(id.isNullOrBlank()){
            call.respond(message = "Invalid question id", status = HttpStatusCode.BadRequest)
            return@get
        }
        val question: QuizQuestion? = quizQuestions.find { it.id == id }
        if (question != null) {
            call.respond(message = question, status = HttpStatusCode.OK)
        }else{
            call.respond(message = "Question not found", status = HttpStatusCode.NotFound)
        }
    }
}