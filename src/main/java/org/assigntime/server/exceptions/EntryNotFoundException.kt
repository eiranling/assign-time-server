package org.assigntime.server.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class EntryNotFoundException(message: String, innerException : Exception?) : NotFoundException(message, innerException) {
    constructor(message: String) : this(message, null)
    constructor() : this("Not found", null)
}