package org.assigntime.server.service

import org.assigntime.server.data.Entry
import org.assigntime.server.exceptions.UnauthorizedException
import org.assigntime.server.interfaces.IDataService
import org.assigntime.server.rules.BREntry
import org.assigntime.server.rules.BRToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EntryService(@Autowired private val brEntry: BREntry,
                   private val brToken: BRToken) {
    fun list(token: String, userId: Long): List<Entry> {
        if (brToken.validateTokenBelongsToUser(token, userId)) {
            return brEntry.listAll(userId)
        }
        throw UnauthorizedException()
    }

    fun getById(token: String, userId: Long, id: Long): Entry {
        if (brToken.validateTokenBelongsToUser(token, userId)) {
            return brEntry.getOne(id)
        }
        throw UnauthorizedException()
    }

    fun create(token: String, userId: Long, newItem: Entry): Entry {
        if (brToken.validateTokenBelongsToUser(token, userId)) {
            return brEntry.create(newItem)
        }
        throw UnauthorizedException()
    }

    fun patch(oldId: Long, newItem: Entry): Entry {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun put(id: Long, newItem: Entry): Entry {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}