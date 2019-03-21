package org.assigntime.server.exceptions

class UserNotFoundException(message: String, innerException : Exception?) : NotFoundException(message, innerException) {
    constructor(message: String) : this(message, null)
    constructor() : this("Not found", null)
}