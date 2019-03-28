package org.assigntime.server.service

import org.assigntime.server.data.Login
import org.assigntime.server.data.TokenDTO
import org.assigntime.server.rules.BRLogin
import org.assigntime.server.rules.BRToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService(@Autowired private val brLogin: BRLogin,
                   private val brToken: BRToken) {


    fun login(loginDetails: Login): TokenDTO {
        return brLogin.login(loginDetails.email, loginDetails.password).toDto()
    }

    fun logout(token: String) {
        if (brToken.validateToken(token)) {
            brLogin.logout(token)
        }
    }
}