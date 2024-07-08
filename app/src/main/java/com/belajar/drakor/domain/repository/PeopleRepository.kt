package com.belajar.drakor.domain.repository

import com.belajar.drakor.domain.model.request.Person

interface PeopleRepository {
    suspend fun getPopularPeople(language: String, page: Int): List<Person>
}