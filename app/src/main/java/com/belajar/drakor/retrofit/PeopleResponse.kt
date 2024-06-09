package com.belajar.drakor.retrofit

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)
