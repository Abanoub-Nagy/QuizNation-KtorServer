package com.example.presentaion.routes.issue_report

import com.example.domin.model.IssueReport
import com.example.domin.repository.IssueReportRepository
import com.example.domin.util.onFailure
import com.example.domin.util.onSuccess
import com.example.presentaion.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.insertIssueReport(
    repository: IssueReportRepository
) {
    post<IssueReportRoutesPath> {
        val issueReport = call.receive<IssueReport>()
        repository.insertIssueReport(issueReport)
            .onSuccess {
                call.respond(
                    message = "Report submitted successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }

    }
}