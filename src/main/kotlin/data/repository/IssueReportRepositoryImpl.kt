package com.example.data.repository

import com.example.data.database.entity.IssueReportEntity
import com.example.data.mapper.IssueReportEntity
import com.example.data.mapper.toIssueReport
import com.example.data.util.Constant.ISSUE_REPORTS_COLLECTION_NAME
import com.example.domain.model.IssueReport
import com.example.domain.repository.IssueReportRepository
import com.example.domain.util.DataError
import com.example.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class IssueReportRepositoryImpl(
    mongoDatabase: MongoDatabase
) : IssueReportRepository {

    private val issueReportCollection = mongoDatabase
        .getCollection<IssueReportEntity>(ISSUE_REPORTS_COLLECTION_NAME)

    override suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError> {
        return try {
            val reports = issueReportCollection
                .find()
                .map { it.toIssueReport() }
                .toList()
            if (reports.isNotEmpty()) {
                Result.Success(reports)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun insertIssueReport(report: IssueReport): Result<Unit, DataError> {
        return try {
            issueReportCollection.insertOne(report.IssueReportEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteIssueReport(id: String?): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                IssueReportEntity::_id.name, id
            )
            val deleteResult = issueReportCollection.deleteOne(filterQuery)
            if (deleteResult.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}