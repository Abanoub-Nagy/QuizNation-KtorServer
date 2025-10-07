package com.example.presentation.routes.path

import io.ktor.resources.Resource

@Resource("/quiz/questions")
class QuizQuestionRoutesPath(
    val quizTopicCode: Int? = null
) {
    @Resource("{questionId}")
    data class ById(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(), val questionId: String
    )

    @Resource("bulk")
    data class Bulk(val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath())

    @Resource("random")
    data class Random(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val quizTopicCode: Int? = null,
        val limit: Int? = null
    )
}