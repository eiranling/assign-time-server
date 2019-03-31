package org.assigntime.server.repository

import org.assigntime.server.data.Entry
import org.assigntime.server.data.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EntryRepository : JpaRepository<Entry, Long> {
    fun findByOwner(user: User) : List<Entry>
}