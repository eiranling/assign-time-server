package org.assigntime.server.interfaces

interface IDataService<T> {
    fun list(): List<T>

    fun getById(id : Long) : T

    fun create(newItem : T) : T

    fun patch(oldId: Long, newItem: T) : T

    fun put(id: Long, newItem: T) : T

    fun delete(id: Long) : T
}