package org.assigntime.server.rules

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class BRPasswordEncoder(@Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    fun encryptPassword(rawPassword: String) : String {
        return bCryptPasswordEncoder.encode(rawPassword)
    }

    fun validatePassword(rawPassword: String, encryptedPassword: String) : Boolean {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword)
    }
}