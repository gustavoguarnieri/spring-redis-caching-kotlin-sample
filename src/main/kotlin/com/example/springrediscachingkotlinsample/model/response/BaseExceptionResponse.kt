package com.example.springrediscachingkotlinsample.model.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseExceptionResponse(
    val timestamp: LocalDateTime? = null,
    val status: Int = 0,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null
)
