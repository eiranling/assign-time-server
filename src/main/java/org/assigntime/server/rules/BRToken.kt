package org.assigntime.server.rules

import org.assigntime.server.data.User
import org.assigntime.server.exceptions.UserNotFoundException
import org.assigntime.server.repository.TokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BRToken(@Autowired private val tokenRepository: TokenRepository) {
    fun findUserByToken(token: String): User {
        val tokens = tokenRepository.findByToken(token)
        return when {
            tokens.size == 1 -> {
                tokens[0].user
            }
            else -> {
                throw UserNotFoundException()
            }
        }
    }
}