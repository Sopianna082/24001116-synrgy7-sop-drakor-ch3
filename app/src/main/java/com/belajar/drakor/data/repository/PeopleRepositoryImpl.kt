package com.belajar.drakor.data.repository

import com.belajar.drakor.data.datasource.mapper.toDomainModel
import com.belajar.drakor.data.datasource.remote.PeopleRemoteDataSource
import com.belajar.drakor.domain.model.request.Person
import com.belajar.drakor.domain.repository.PeopleRepository

class PeopleRepositoryImpl(private val remoteDataSource: PeopleRemoteDataSource) :
    PeopleRepository {
    override suspend fun getPopularPeople(language: String, page: Int): List<Person> {
        return remoteDataSource.getPopularPeople(language, page).toDomainModel()
    }
}
