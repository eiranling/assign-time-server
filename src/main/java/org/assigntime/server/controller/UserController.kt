package org.assigntime.server.controller

import org.assigntime.server.data.User
import org.assigntime.server.repository.UserRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = userRepository.findAll()

    @PostMapping("/users")
    fun createNewUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        return if (userRepository.exists(Example.of(User(email = user.email)))) {
            ResponseEntity.ok(userRepository.save(user))
        } else {
            ResponseEntity.badRequest().build()
        }

    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User> {
        return userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/users/{id}")
    fun updateUserById(@PathVariable(value = "id") userId: Long,
                       @Valid @RequestBody user: User): ResponseEntity<User> {
        return userRepository.findById(userId).map { existingUser ->
            val updatedUser : User = existingUser
                    .copy(email = user.email, firstName = user.firstName)
            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/articles/{id}")
    fun deleteUserById(@PathVariable(value = "id") userId: Long) : ResponseEntity<Void> {
        return userRepository.findById(userId).map { existingUser ->
            userRepository.delete(existingUser)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}
