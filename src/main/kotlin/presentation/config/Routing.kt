package com.example.presentation.config

import com.example.domain.repository.IssueReportRepository
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.repository.QuizTopicRepository
import com.example.presentation.routes.issueReportRoutes
import com.example.presentation.routes.quizQuestionsRoutes
import com.example.presentation.routes.quizTopicsRoutes
import com.example.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    install(Resources)

    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()

    routing {
        root()
        //Quiz Question
        quizQuestionsRoutes(quizQuestionRepository)
        //Quiz Topic
        quizTopicsRoutes(quizTopicRepository)
        //Issue Report
        issueReportRoutes(issueReportRepository)
        staticResources(
            remotePath = "/images", basePackage = "images"
        )
    }
}

