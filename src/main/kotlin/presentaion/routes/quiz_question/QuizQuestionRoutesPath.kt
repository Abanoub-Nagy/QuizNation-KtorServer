package com.example.presentaion.routes.quiz_question

import io.ktor.resources.*

@Resource("/quiz/questions")
class QuizQuestionRoutesPath(
    val quizTopicCode: Int? = null,
    val limit: Int? = null
) {
    @Resource("/{questionId}")
    data class ById(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val questionId: String
    )
}