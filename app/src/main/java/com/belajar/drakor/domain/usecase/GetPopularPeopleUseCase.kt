package com.belajar.drakor.domain.usecase

import com.belajar.drakor.domain.model.request.Person
import com.belajar.drakor.domain.repository.PeopleRepository

class GetPopularPeopleUseCase(private val peopleRepository: PeopleRepository) {
    suspend operator fun invoke(language: String, page: Int): List<Person> {
        return peopleRepository.getPopularPeople(language, page)
    }
}
