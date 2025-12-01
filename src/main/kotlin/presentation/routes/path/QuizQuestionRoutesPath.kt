package com.example.presentation.routes.path

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

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

    @Serializable
    @Resource("/random")
    data class Random(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val topicCode: Int? = null,  // ← Match Android parameter name
        val limit: Int? = null
    )
}