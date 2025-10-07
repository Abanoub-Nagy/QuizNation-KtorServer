package com.example.domain.repository

import com.example.domain.model.IssueReport
import com.example.domain.util.DataError
import com.example.domain.util.Result

interface IssueReportRepository {
    suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError>
    suspend fun insertIssueReport(report: IssueReport): Result<Unit, DataError>
    suspend fun deleteIssueReport(id: String?): Result<Unit, DataError>
}