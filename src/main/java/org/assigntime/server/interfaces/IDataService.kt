package org.assigntime.server.interfaces

interface IDataService<Base, DTO> {
    fun list(): List<DTO>

    fun getById(id: Long): DTO

    fun create(newItem: Base): DTO

    fun patch(oldId: Long, newItem: Base): DTO

    fun put(id: Long, newItem: Base): DTO

    fun delete(id: Long)
}