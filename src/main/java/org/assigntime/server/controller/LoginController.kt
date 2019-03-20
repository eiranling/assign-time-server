package org.assigntime.server.controller

import org.assigntime.server.data.Login
import org.assigntime.server.data.User
import org.assigntime.server.repository.TokenRepository
import org.assigntime.server.repository.UserRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LoginController(private val tokenRepository: TokenRepository, private val userRepository: UserRepository) {
    @PostMapping("/login")
    fun login(@RequestBody loginDetails: Login) {
        userRepository.findAll(Example.of(User(email = loginDetails.email), ExampleMatcher.matchingAny()))
    }
}