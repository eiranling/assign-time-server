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

    fun validateToken(token: String) : Boolean {
        val tokens = tokenRepository.findByToken(token)
        return tokens.any()
    }

    fun validateTokenBelongsToUser(token: String, userId: Long) : Boolean {
        val tokens = tokenRepository.findByToken(token)
        if (tokens.size == 1) {
            return tokens.first().user.id == userId
        }
        return false
    }
}