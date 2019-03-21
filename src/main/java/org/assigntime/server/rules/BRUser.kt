package org.assigntime.server.rules

import org.assigntime.server.data.User
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
        val user = findUserById(userId)





        return User().copy(
                id = user.id,
        )
    }
}