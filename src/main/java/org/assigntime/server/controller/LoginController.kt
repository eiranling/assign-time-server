package org.assigntime.server.controller

import org.assigntime.server.data.Login
import org.assigntime.server.data.TokenDTO
import org.assigntime.server.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class LoginController(private val loginService: LoginService) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginDetails: Login): ResponseEntity<TokenDTO> {
        return ResponseEntity.ok(loginService.login(loginDetails))
    }

    @PostMapping("/logout")
    fun logout(@Valid @RequestBody token: TokenDTO): ResponseEntity<Void> {
        loginService.logout(token)
        return ResponseEntity.ok().build()
    }
}