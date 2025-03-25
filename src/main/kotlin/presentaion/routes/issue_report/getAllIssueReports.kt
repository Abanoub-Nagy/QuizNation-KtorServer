package com.example.presentaion.routes.issue_report

import com.example.domin.repository.IssueReportRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.getAllIssueReports(
    repository: IssueReportRepository
) {
    get<IssueReportRoutesPath> {
        repository.getAllIssueReports()
            .onSuccess { reports ->
                call.respond(
                    message = reports,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}