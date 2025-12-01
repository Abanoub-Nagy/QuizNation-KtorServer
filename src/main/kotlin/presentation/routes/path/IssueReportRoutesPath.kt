package com.example.presentation.routes.path

import io.ktor.resources.Resource

@Resource(path = "/report/issues")
class IssueReportRoutesPath {

    @Resource(path = "{reportId}")
    data class ById(
        val parent: IssueReportRoutesPath = IssueReportRoutesPath(),
        val reportId: String
    )
}