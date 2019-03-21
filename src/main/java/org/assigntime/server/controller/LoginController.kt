package org.assigntime.server.controller

import org.assigntime.server.data.Login
import org.assigntime.server.data.Token
import org.assigntime.server.data.TokenDTO
import org.assigntime.server.data.User
import org.assigntime.server.repository.TokenRepository
import org.assigntime.server.repository.UserRepository
import org.assigntime.server.security.TokenStore
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.criteria.CriteriaBuilder

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
            bCryptPasswordEncoder.matches(loginDetails.password, users[0].password) -> ResponseEntity.ok(
                    Token(tokenStore.generateAndStoreToken(users[0].id), users[0])
                            .toDto())
            else -> {
                System.out.println("Bad Password")
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
            }
        }
    }
}