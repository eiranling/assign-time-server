package org.assigntime.server.rules

import org.assigntime.server.data.Entry
import org.assigntime.server.repository.EntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BREntry(@Autowired private val entryRepository: EntryRepository) {
    fun listAll(userId: Long): List<Entry> {
        return entryRepository.findByOwner(userId)
    }
}