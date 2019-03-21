package org.assigntime.server.exceptions

abstract class NotFoundException(message: String, innerException : Exception?) : Exception(message, innerException) {
    constructor(message: String) : this(message, null)
    constructor() : this("Not found", null)
}