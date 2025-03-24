package com.example.domin.util

sealed interface DataError : Error {
    data object NotFound : DataError
    data object Database : DataError
    data object Validation : DataError
    data object Unknown : DataError
}