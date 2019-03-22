package org.assigntime.server.exceptions

class BadRequestException(message: String, innerException: Exception?) : Exception(message, innerException) {
    constructor(message: String) : this(message, null)
    constructor() : this("Bad Request", null)
}