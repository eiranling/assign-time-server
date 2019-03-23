package org.assigntime.server.service

import org.assigntime.server.data.Entry
import org.assigntime.server.interfaces.IDataService
import org.assigntime.server.rules.BREntry
import org.assigntime.server.rules.BRToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EntryService(@Autowired private val brEntry: BREntry,
                   private val brToken: BRToken) {
    fun list(): List<Entry> {
        return brEntry.listAll(0)
    }

    fun getById(id: Long): Entry {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun create(newItem: Entry): Entry {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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