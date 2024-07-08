package com.belajar.drakor.data.datasource.mapper

import com.belajar.drakor.data.datasource.remote.model.response.PeopleResponse
import com.belajar.drakor.domain.model.request.Person

fun PeopleResponse.toDomainModel(): List<Person> {
    return results.map { Person(it.id, it.name, it.profile_path) }
}