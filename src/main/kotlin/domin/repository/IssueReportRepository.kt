package com.example.domin.repository

import com.example.domin.model.IssueReport
import com.example.domin.util.DataError
import com.example.domin.util.Result

interface IssueReportRepository {
    suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError>
    suspend fun insertIssueReport(report: IssueReport): Result<Unit, DataError>
    suspend fun deleteIssueReport(id: String?): Result<Unit, DataError>
}