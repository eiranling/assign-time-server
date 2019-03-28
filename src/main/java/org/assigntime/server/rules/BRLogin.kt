package org.assigntime.server.rules

import org.assigntime.server.data.Token
import org.assigntime.server.exceptions.BadRequestException
import org.assigntime.server.exceptions.UnauthorizedException
import org.assigntime.server.repository.TokenRepository
import org.assigntime.server.repository.UserRepository
import org.assigntime.server.security.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BRLogin(@Autowired private val tokenRepository: TokenRepository,
              private val userRepository: UserRepository,
              private val tokenStore: TokenStore,
              private val brPasswordEncoder: BRPasswordEncoder) {
    fun login(email: String, password: String): Token {
        val users = userRepository.findByEmail(email)
        return when {
            users.size > 1 -> throw BadRequestException()
            users.isEmpty() -> {
                throw UnauthorizedException()
            }
            brPasswordEncoder.validatePassword(password, users[0].password) -> {
                val token = if (tokenStore.retrieveToken(users[0].id) != null) {
                    tokenStore.retrieveToken(users[0].id).toString()
                } else {
                    val resultToken = tokenRepository.findByUserId(users[0].id).firstOrNull()
                    resultToken?.token ?: tokenStore.generateAndStoreToken(users[0].id)
                }
                tokenRepository.save(Token(token, users[0]))
            }
            else -> {
                throw UnauthorizedException()
            }
        }
    }

    fun logout(token: String) {

        val tokens = tokenRepository.findByToken(token)
        val validToken = tokens.firstOrNull()
        return when {
            validToken != null -> {
                tokenRepository.delete(validToken)
            }
            else -> {
                throw UnauthorizedException()
            }
        }
    }
}