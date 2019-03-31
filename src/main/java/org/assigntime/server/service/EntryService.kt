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
            return brEntry.create(newItem, userId)
        }
        throw UnauthorizedException()
    }

    fun patch(token: String, userId: Long, entryId: Long, newItem: Entry): Entry {
        if (brToken.validateTokenBelongsToUser(token, userId)) {
            return brEntry.patch(entryId, newItem)
        }
        throw UnauthorizedException()
    }

    fun put(token: String, userId: Long, id: Long, newItem: Entry) : Entry {
        if (brToken.validateTokenBelongsToUser(token, userId)) {
            return brEntry.put(id, newItem)
        }
        throw UnauthorizedException()
    }

    fun delete(token:String, userId: Long, id: Long) {
        if (brToken.validateTokenBelongsToUser(token, userId)) {
            return brEntry.delete(id)
        }
        throw UnauthorizedException()
    }

}