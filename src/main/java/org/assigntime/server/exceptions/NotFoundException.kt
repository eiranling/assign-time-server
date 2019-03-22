package org.assigntime.server.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
abstract class NotFoundException(message: String, innerException : Exception?) : Exception(message, innerException) {
    constructor(message: String) : this(message, null)
    constructor() : this("Not found", null)
}