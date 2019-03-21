package org.assigntime.server.rules

import org.assigntime.server.data.User
import org.assigntime.server.exceptions.UserNotFoundException
import org.assigntime.server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BRUser(@Autowired private val userRepository: UserRepository) {
    fun listUsers() : List<User> {
        return userRepository.findAll()
    }

    fun findUserById(userId: Long) : User? {
        return userRepository.findByIdOrNull(userId)
    }

    fun saveUser(userId: Long) {

    }

    fun userExists(userId: Long) : Boolean {
        return userRepository.existsById(userId)
    }

    fun copyNonDefaults(userId : Long, newUser : User) : User {
        val user = findUserById(userId) ?: throw UserNotFoundException()
        return User().copy(
                id = user.id,
                firstName = if (newUser.firstName == "") user.firstName else newUser.firstName,
                lastName = if (newUser.lastName == "") user.lastName else newUser.lastName,
                email = if (newUser.email == "") user.email else newUser.email,
                password = if (newUser.password == "") user.password else newUser.password
        )
    }
}