package org.assigntime.server.controller

import org.assigntime.server.data.Entry
import org.assigntime.server.repository.EntryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
}