package org.assigntime.server.rules

import org.apache.commons.logging.Log
import org.apache.logging.log4j.Logger
import org.assigntime.server.data.Entry
import org.assigntime.server.data.RecurrenceFrequency
import org.assigntime.server.exceptions.EntryNotFoundException
import org.assigntime.server.repository.EntryRepository
import org.assigntime.server.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BREntry(@Autowired private val entryRepository: EntryRepository,
              private val userRepository: UserRepository) {
    fun listAll(userId: Long): List<Entry> {
        val user = userRepository.findById(userId)
        return entryRepository.findByOwner(user.get())
    }

    fun getOne(entryId: Long) : Entry {
        return entryRepository.getOne(entryId)
    }

    fun create(newEntry: Entry, userId: Long) : Entry {
        val user = userRepository.findById(userId)
        newEntry.owner = user.get()
        return entryRepository.save(newEntry)
    }

    fun patch(entryId: Long, newEntry: Entry) : Entry {
        val oldEntry = entryRepository.findById(entryId)
        if (oldEntry.isPresent) {
            val entry = Entry().copy(
                    id = entryId,
                    owner = oldEntry.get().owner,
                    recurrenceFrequency = if (newEntry.recurrenceFrequency != RecurrenceFrequency.NONE) newEntry.recurrenceFrequency else oldEntry.get().recurrenceFrequency,
                    startDateTime = if (newEntry.startDateTime != Date(Long.MIN_VALUE)) newEntry.startDateTime else oldEntry.get().startDateTime,
                    endDateTime = if (newEntry.endDateTime != Date(Long.MIN_VALUE)) newEntry.endDateTime else oldEntry.get().endDateTime,
                    title = if (newEntry.title != "") newEntry.title else oldEntry.get().title
            )
            entryRepository.save(entry)
        }
        throw EntryNotFoundException()
    }

    fun put(entryId: Long, newEntry: Entry) : Entry {
        val oldEntry = entryRepository.findById(entryId)
        LoggerFactory.getLogger(this.javaClass).debug("PUT Request received")
        if (oldEntry.isPresent) {
            val entry = Entry().copy(
                    id = entryId,
                    owner = oldEntry.get().owner,
                    recurrenceFrequency = newEntry.recurrenceFrequency,
                    startDateTime = if (newEntry.startDateTime != Date(Long.MIN_VALUE)) newEntry.startDateTime else oldEntry.get().startDateTime,
                    endDateTime = if (newEntry.endDateTime != Date(Long.MIN_VALUE)) newEntry.endDateTime else oldEntry.get().endDateTime,
                    title = newEntry.title
            )
            return entryRepository.save(entry)
        }
        throw EntryNotFoundException()
    }

    fun delete(entryId: Long) {
        val entry = entryRepository.findById(entryId)
        if (entry.isPresent) {
            entryRepository.delete(entry.get())
        } else {
            throw EntryNotFoundException()
        }
    }
}