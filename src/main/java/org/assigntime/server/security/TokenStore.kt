package org.assigntime.server.security

import java.util.*

class TokenStore() {
    companion object {
        var tokenStore = HashMap<Long, String>()
    }

    fun retrieveToken(userId : Long) : String? = tokenStore[userId]

    fun generateAndStoreToken(userId : Long) : String {
        val token = UUID.randomUUID().toString()
        tokenStore[userId] = token
        return token
    }
}