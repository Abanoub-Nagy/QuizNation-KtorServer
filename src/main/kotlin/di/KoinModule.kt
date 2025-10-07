package com.example.di

import com.example.data.database.DatabaseFactory
import com.example.data.repository.IssueReportRepositoryImpl
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.data.repository.QuizTopicRepositoryImpl
import com.example.domain.repository.IssueReportRepository
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.repository.QuizTopicRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val koinModule = module {
    single { DatabaseFactory.create() }
    singleOf(::QuizQuestionRepositoryImpl).bind<QuizQuestionRepository>()
    singleOf(::QuizTopicRepositoryImpl).bind<QuizTopicRepository>()
    singleOf(::IssueReportRepositoryImpl).bind<IssueReportRepository>()
}