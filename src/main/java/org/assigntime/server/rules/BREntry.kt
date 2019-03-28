package org.assigntime.server.rules

import org.assigntime.server.data.Entry
import org.assigntime.server.data.RecurrenceFrequency
import org.assigntime.server.exceptions.EntryNotFoundException
import org.assigntime.server.repository.EntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BREntry(@Autowired private val entryRepository: EntryRepository) {
    fun listAll(userId: Long): List<Entry> {
        return entryRepository.findByOwner(userId)
    }

    fun getOne(entryId: Long) : Entry {
        return entryRepository.getOne(entryId)
    }

    fun create(newEntry: Entry) : Entry {
        return entryRepository.save(newEntry)
    }

    fun patch(entryId: Long, newEntry: Entry) : Entry {
        val oldEntry = entryRepository.findById(entryId)
        if (oldEntry.isPresent) {
            val entry = Entry().copy(
                    id = entryId,
                    owner = oldEntry.get().owner,
                    recurrenceFrequency = if (newEntry.recurrenceFrequency != RecurrenceFrequency.NONE) newEntry.recurrenceFrequency else oldEntry.get().recurrenceFrequency,
                    dateTime = if (newEntry.dateTime != Date(Long.MIN_VALUE)) newEntry.dateTime else oldEntry.get().dateTime,
                    title = if (newEntry.title != "") newEntry.title else oldEntry.get().title
            )
            entryRepository.save(entry)
        }
        throw EntryNotFoundException()
    }

    fun put(entryId: Long, newEntry: Entry) : Entry {
        val oldEntry = entryRepository.findById(entryId)
        if (oldEntry.isPresent) {
            val entry = Entry().copy(
                    id = entryId,
                    owner = oldEntry.get().owner,
                    recurrenceFrequency = newEntry.recurrenceFrequency,
                    dateTime = newEntry.dateTime,
                    title = newEntry.title
            )
            entryRepository.save(entry)
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