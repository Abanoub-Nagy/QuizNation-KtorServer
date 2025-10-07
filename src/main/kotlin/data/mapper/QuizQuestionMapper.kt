package com.example.data.mapper

import com.example.data.database.entity.QuizQuestionEntity
import com.example.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion(
    id = _id,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    quizTopicCode = quizTopicCode,
)

fun QuizQuestion.toQuizQuestionEntity() = QuizQuestionEntity(
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    quizTopicCode = quizTopicCode,
)