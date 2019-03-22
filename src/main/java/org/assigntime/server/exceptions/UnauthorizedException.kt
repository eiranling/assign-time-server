package org.assigntime.server.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class UnauthorizedException(message: String, innerException: Exception?) : Exception(message, innerException) {
    constructor(message: String) : this(message, innerException = null)
    constructor() : this("Unauthorized", null)
}