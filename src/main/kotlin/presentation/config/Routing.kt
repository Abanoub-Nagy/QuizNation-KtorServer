package com.example.presentation.config

import com.example.domain.repository.IssueReportRepository
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.repository.QuizTopicRepository
import com.example.presentation.routes.issue_report.deleteIssueReportById
import com.example.presentation.routes.issue_report.getAllIssueReports
import com.example.presentation.routes.issue_report.insertIssueReport
import com.example.presentation.routes.quiz_question.*
import com.example.presentation.routes.quiz_topic.deleteQuizTopicById
import com.example.presentation.routes.quiz_topic.getAllQuizTopics
import com.example.presentation.routes.quiz_topic.getQuizTopicById
import com.example.presentation.routes.quiz_topic.upsertQuizTopic
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
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        insertQuizQuestionInBulk(quizQuestionRepository)
        getRandomQuizQuestions(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        getAllQuizQuestionById(quizQuestionRepository)

        //Quiz Topic
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)

        //Issue Report
        getAllIssueReports(issueReportRepository)
        deleteIssueReportById(issueReportRepository)
        insertIssueReport(issueReportRepository)

        staticResources(
            remotePath = "/images", basePackage = "images"
        )
    }
}

