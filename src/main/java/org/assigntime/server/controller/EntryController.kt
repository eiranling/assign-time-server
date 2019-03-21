package org.assigntime.server.controller

import org.assigntime.server.data.Entry
import org.assigntime.server.data.RecurrenceFrequency
import org.assigntime.server.repository.EntryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/api")
class EntryController(private val entryRepository: EntryRepository) {

    @GetMapping("/users/{id}/entries")
    fun getAllEntries(@PathVariable(name = "id") userId : Long) : ResponseEntity<List<Entry>> {
        return ResponseEntity.ok(entryRepository.findByOwner(userId))
    }

    @GetMapping("/users/{id}/entries/{entryId}")
    fun getEntry(@PathVariable(name = "id") userId: Long,
                 @PathVariable(name = "entryId") entryId: Long) : ResponseEntity<Entry> {
        return entryRepository.findById(entryId).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/users/{id}/entries")
    fun createEntry(@PathVariable(name = "id") userId :Long,
                    @RequestBody newEntry: Entry) : ResponseEntity<Entry> {
        return ResponseEntity.ok(entryRepository.save(newEntry))
    }

    @PatchMapping("/users/{id}/entries/{entryId}")
    fun patchEntry(@PathVariable(name = "id") userID: Long,
                   @PathVariable(name = "entryId") entryId: Long,
                   @RequestBody updatedEntry: Entry) : ResponseEntity<Entry> {
        return entryRepository.findById(entryId).map {
            ResponseEntity.ok(entryRepository.save(Entry().copy(
                    id = it.id,
                    title = if (updatedEntry.title.isNotBlank()) updatedEntry.title else it.title,
                    dateTime = if (updatedEntry.dateTime != Date(Long.MIN_VALUE)) updatedEntry.dateTime else it.dateTime,
                    recurrenceFrequency = if (updatedEntry.recurrenceFrequency != RecurrenceFrequency.NONE) updatedEntry.recurrenceFrequency else it.recurrenceFrequency,
                    owner = it.owner)))
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/users/{id}/entries/{entryId}")
    fun putEntry(@PathVariable(name = "id") userID: Long,
                   @PathVariable(name = "entryId") entryId: Long,
                   @RequestBody updatedEntry: Entry) : ResponseEntity<Entry> {
        return entryRepository.findById(entryId).map {
            ResponseEntity.ok(entryRepository.save(Entry().copy(
                    id = it.id,
                    title = updatedEntry.title,
                    dateTime = updatedEntry.dateTime,
                    recurrenceFrequency = updatedEntry.recurrenceFrequency,
                    owner = it.owner)))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/users/{id}/entries/{entryId}")
    fun deleteEntry(@PathVariable(name = "id") userID: Long,
                    @PathVariable(name = "entryId") entryId: Long,
                    @RequestBody updatedEntry: Entry) : ResponseEntity<Void> {
        return entryRepository.findById(entryId).map { existingEntry ->
            entryRepository.delete(existingEntry)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}