package org.assigntime.server.service

import org.assigntime.server.data.User
import org.assigntime.server.interfaces.IDataService
import org.assigntime.server.repository.UserRepository
import org.assigntime.server.rules.BRUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val brUser: BRUser) : IDataService<User> {

    override fun list(): List<User> {
        return brUser.listUsers()
    }

    override fun getById(id: Long): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(newItem: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun patch(oldId: Long, newItem: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(id: Long, newItem: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}