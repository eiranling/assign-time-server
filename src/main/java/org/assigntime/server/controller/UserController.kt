package org.assigntime.server.controller

import org.assigntime.server.data.User
import org.assigntime.server.data.UserDTO
import org.assigntime.server.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    fun getAllUsers(): List<UserDTO> = userService.list()

    @PostMapping("/users")
    fun createNewUser(@Valid @RequestBody user: User): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.create(user))
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.getById(userId))
    }

    @PutMapping("/users/{id}")
    fun updateUserById(@PathVariable(value = "id") userId: Long,
                       @Valid @RequestBody user: User): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.put(userId, user))
    }

    @DeleteMapping("/users/{id}")
    fun deleteUserById(@PathVariable(value = "id") userId: Long) : ResponseEntity<Void> {
        userService.delete(userId)
        return ResponseEntity.ok().build()

    }
}
