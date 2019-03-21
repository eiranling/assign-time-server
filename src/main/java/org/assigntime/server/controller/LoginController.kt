package org.assigntime.server.controller

import org.assigntime.server.data.Login
import org.assigntime.server.data.Token
import org.assigntime.server.data.TokenDTO
import org.assigntime.server.repository.TokenRepository
import org.assigntime.server.repository.UserRepository
import org.assigntime.server.security.TokenStore
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LoginController(private val tokenRepository: TokenRepository,
                      private val userRepository: UserRepository,
                      private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                      private val tokenStore : TokenStore) {
    @PostMapping("/login")
    fun login(@RequestBody loginDetails: Login) : ResponseEntity<TokenDTO> {

        val users = userRepository.findByEmail(loginDetails.email)
        return when {
            users.size > 1 -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            users.isEmpty() -> {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
            }
            bCryptPasswordEncoder.matches(loginDetails.password, users[0].password) -> {
                val token = if (tokenStore.retrieveToken(users[0].id) != null) {
                    tokenStore.retrieveToken(users[0].id).toString()
                } else {
                    val resultToken = tokenRepository.findByUserId(users[0].id).firstOrNull()
                    resultToken?.token ?: tokenStore.generateAndStoreToken(users[0].id)
                }
                tokenRepository.save(Token(token, users[0]))
                ResponseEntity.ok(Token(token, users[0]).toDto())
            }
            else -> {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
            }
        }
    }

    @PostMapping("/logout")
    fun logout(@RequestBody token : TokenDTO) : ResponseEntity<Void> {
        val tokens = tokenRepository.findByToken(token.token)
        val validToken = tokens.firstOrNull { it.user.id == token.userId }
        return when {
            validToken != null -> {
                tokenStore.deleteToken(token.userId)
                tokenRepository.delete(validToken)
                ResponseEntity.ok().build()
            } else -> {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
            }
        }
    }
}