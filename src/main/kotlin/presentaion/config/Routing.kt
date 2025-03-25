package com.example.presentaion.config

import com.example.data.database.DatabaseFactory
import com.example.data.repository.IssueReportRepositoryImpl
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.data.repository.QuizTopicRepositoryImpl
import com.example.domin.repository.QuizQuestionRepository
import com.example.domin.repository.QuizTopicRepository
import com.example.presentaion.routes.issue_report.deleteIssueReportById
import com.example.presentaion.routes.issue_report.getAllIssueReports
import com.example.presentaion.routes.issue_report.insertIssueReport
import com.example.presentaion.routes.quiz_question.deleteQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestionById
import com.example.presentaion.routes.quiz_question.getAllQuizQuestions
import com.example.presentaion.routes.quiz_question.upsertQuizQuestion
import com.example.presentaion.routes.quiz_topic.deleteQuizTopicById
import com.example.presentaion.routes.quiz_topic.getAllQuizTopics
import com.example.presentaion.routes.quiz_topic.getQuizTopicById
import com.example.presentaion.routes.quiz_topic.upsertQuizTopic
import com.example.presentaion.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    install(Resources)

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)
    val quizTopicRepository: QuizTopicRepository = QuizTopicRepositoryImpl(mongoDatabase)
    val issueReportRepository = IssueReportRepositoryImpl(mongoDatabase)
    routing {
        root()

        //Quiz Question
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
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
            remotePath = "/images",
            basePackage = "images"
        )
    }
}

