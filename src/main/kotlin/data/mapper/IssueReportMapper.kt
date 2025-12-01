package com.example.data.mapper

import com.example.data.database.entity.IssueReportEntity
import com.example.domain.model.IssueReport

fun IssueReportEntity.toIssueReport() = IssueReport(
    id = _id,
    quizQuestionId = quizQuestionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)

fun IssueReport.IssueReportEntity() = IssueReportEntity(
    quizQuestionId = quizQuestionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)