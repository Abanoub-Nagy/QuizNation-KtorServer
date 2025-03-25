package com.example.presentaion.routes.issue_report

import io.ktor.resources.*

@Resource(path = "/report/issues")
class IssueReportRoutesPath {

    @Resource(path = "{reportId}")
    data class ById(
        val parent: IssueReportRoutesPath = IssueReportRoutesPath(),
        val reportId: String
    )
}
