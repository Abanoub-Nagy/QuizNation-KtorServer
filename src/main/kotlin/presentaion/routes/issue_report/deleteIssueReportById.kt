package com.example.presentaion.routes.issue_report

import com.example.domin.repository.IssueReportRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
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