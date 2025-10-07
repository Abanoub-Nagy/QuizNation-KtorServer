package com.example.presentation.routes.issue_report

import com.example.domain.repository.IssueReportRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteIssueReportById(
    repository: IssueReportRepository
) {
    delete<IssueReportRoutesPath.ById> { path ->
        repository.deleteIssueReport(path.reportId)
            .onSuccess {
                call.respond(
                    HttpStatusCode.NoContent
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}