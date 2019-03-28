package org.assigntime.server.controller

import org.assigntime.server.data.Entry
import org.assigntime.server.data.RecurrenceFrequency
import org.assigntime.server.repository.EntryRepository
import org.assigntime.server.service.EntryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/api")
class EntryController(private val entryRepository: EntryRepository,
                      private val entryService: EntryService) {

    @GetMapping("/users/{id}/entries")
    fun getAllEntries(@RequestHeader(name="X-Authorization") token: String, @PathVariable(name = "id") userId : Long) : ResponseEntity<List<Entry>> {
        return ResponseEntity.ok(entryService.list(token, userId))
    }

    @GetMapping("/users/{id}/entries/{entryId}")
    fun getEntry(@RequestHeader(name="X-Authorization") token: String,
                 @PathVariable(name = "id") userId: Long,
                 @PathVariable(name = "entryId") entryId: Long) : ResponseEntity<Entry> {
        return ResponseEntity.ok(entryService.getById(token, userId, entryId))
    }

    @PostMapping("/users/{id}/entries")
    fun createEntry(@RequestHeader(name="X-Authorization") token: String,
                    @PathVariable(name = "id") userId :Long,
                    @RequestBody newEntry: Entry) : ResponseEntity<Entry> {
        return ResponseEntity.ok(entryService.create(token, userId, newEntry))
    }

    @PatchMapping("/users/{id}/entries/{entryId}")
    fun patchEntry(@RequestHeader(name="X-Authorization") token: String,
                   @PathVariable(name = "id") userID: Long,
                   @PathVariable(name = "entryId") entryId: Long,
                   @RequestBody updatedEntry: Entry) : ResponseEntity<Entry> {
        return ResponseEntity.ok(entryService.patch(token, userID, entryId, updatedEntry))
    }

    @PutMapping("/users/{id}/entries/{entryId}")
    fun putEntry(@RequestHeader(name="X-Authorization") token: String,
                 @PathVariable(name = "id") userID: Long,
                   @PathVariable(name = "entryId") entryId: Long,
                   @RequestBody updatedEntry: Entry) : ResponseEntity<Entry> {
        return ResponseEntity.ok(entryService.put(token, userID, entryId, updatedEntry))
    }

    @DeleteMapping("/users/{id}/entries/{entryId}")
    fun deleteEntry(@RequestHeader(name="X-Authorization") token: String,
                    @PathVariable(name = "id") userID: Long,
                    @PathVariable(name = "entryId") entryId: Long) : ResponseEntity<Void> {
        entryService.delete(token, userID, entryId)
        return ResponseEntity.ok().build()
    }
}