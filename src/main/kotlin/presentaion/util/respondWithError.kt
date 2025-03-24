package com.example.presentaion.util

import com.example.domin.util.DataError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend fun RoutingContext.respondWithError(
    error: DataError,
) {
    when (error) {
        DataError.Database -> {
            call.respond(
                message = "An unknown error occurred",
                status = HttpStatusCode.InternalServerError
            )
        }

        DataError.NotFound -> {
            call.respond(
                message = "No Questions found",
                status = HttpStatusCode.NotFound
            )
        }

        DataError.Unknown -> {
            call.respond(
                message = "An unknown error occurred",
                status = HttpStatusCode.InternalServerError
            )
        }

        DataError.Validation -> {
            call.respond(
                message = "Invalid data provided ",
                status = HttpStatusCode.BadRequest
            )
        }
    }
}