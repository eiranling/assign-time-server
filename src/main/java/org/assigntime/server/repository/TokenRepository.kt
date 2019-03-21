package org.assigntime.server.repository

import org.assigntime.server.data.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository : JpaRepository<Token, String> {
    fun findByToken(token: String) : List<Token>
    fun findByUserId(userId : Long) : List<Token>
}