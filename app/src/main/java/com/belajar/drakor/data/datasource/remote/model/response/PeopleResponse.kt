package com.belajar.drakor.data.datasource.remote.model.response

import com.belajar.drakor.domain.model.request.Person

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)

data class PersonEntity(
    val id: Int,
    val name: String,
    val profile_path: String?
)