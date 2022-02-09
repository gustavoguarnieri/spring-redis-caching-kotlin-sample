package com.example.springrediscachingkotlinsample.config

import com.example.springrediscachingkotlinsample.exceptions.NotFoundException
import com.example.springrediscachingkotlinsample.model.response.BaseExceptionResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandlerConfig {

    @ExceptionHandler(NotFoundException::class)
    private fun handleNotFoundException(
        ex: NotFoundException,
        request: WebRequest
    ): ResponseEntity<BaseExceptionResponse> {
        val baseExceptionResponse = BaseExceptionResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.reasonPhrase,
            ex.localizedMessage,
            (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(baseExceptionResponse, HttpHeaders(), HttpStatus.NOT_FOUND)
    }
}
