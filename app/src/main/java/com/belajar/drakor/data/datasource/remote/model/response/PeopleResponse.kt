package com.belajar.drakor.data.datasource.remote.model.response

import com.belajar.drakor.data.datasource.remote.model.request.Person

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)
