package org.assigntime.server.exceptions

class UnauthorizedException(message: String, innerException: Exception?) : Exception(message, innerException) {
    constructor(message: String) : this(message, innerException = null)
    constructor() : this("Unauthorized", null)
}