package org.assigntime.server.service

import org.assigntime.server.data.User
import org.assigntime.server.data.UserDTO
import org.assigntime.server.exceptions.BadRequestException
import org.assigntime.server.exceptions.UserNotFoundException
import org.assigntime.server.interfaces.IDataService
import org.assigntime.server.rules.BRPasswordEncoder
import org.assigntime.server.rules.BRUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val brUser: BRUser,
                  private val brPasswordEncoder: BRPasswordEncoder) : IDataService<User, UserDTO> {

    override fun list(): List<UserDTO> {
        return brUser.listUsers().map {
            it.toDto()
        }
    }

    override fun getById(id: Long): UserDTO {
        val user = brUser.findUserById(id) ?: throw UserNotFoundException()
        return user.toDto()
    }

    override fun create(newItem: User): UserDTO {
        if (brUser.userWithEmailExists(newItem.email)) {
            throw BadRequestException()
        } else {
            newItem.password = brPasswordEncoder.encryptPassword(newItem.password)
            brUser.saveUser(newItem)
            return newItem.toDto()
        }
    }

    override fun patch(oldId: Long, newItem: User): UserDTO {
        val newUser = brUser.copyNonDefaults(oldId, newItem)
        brUser.saveUser(newUser)
        return newUser.toDto()
    }

    override fun put(id: Long, newItem: User): UserDTO {
        if (newItem.id != id) {
            throw BadRequestException()
        }
        brUser.saveUser(newItem)
        return newItem.toDto()
    }

    override fun delete(id: Long) {
        if (!brUser.userExists(id)) {
            throw UserNotFoundException()
        }
        brUser.removeUser(id)
    }

}