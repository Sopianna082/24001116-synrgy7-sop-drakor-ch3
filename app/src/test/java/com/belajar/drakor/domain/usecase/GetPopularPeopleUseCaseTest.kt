package com.belajar.drakor.domain.usecase

import com.belajar.drakor.domain.model.request.Person
import com.belajar.drakor.domain.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetPopularPeopleUseCaseTest {

    @Mock
    private lateinit var mockRepository: PeopleRepository

    private lateinit var getPopularPeopleUseCase: GetPopularPeopleUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getPopularPeopleUseCase = GetPopularPeopleUseCase(mockRepository)
    }

    @Test
    fun `test get popular people use case`() {
        runBlocking {
            // Given
            val language = "en"
            val page = 1
            // Assuming `profile_path` is of type String
            val mockPeopleList = listOf(
                Person(id = 1, name = "Person 1", profile_path = "/path/to/profile1"),
                Person(id = 2, name = "Person 2", profile_path = "/path/to/profile2")
            )

            // Mock repository response
            `when`(mockRepository.getPopularPeople(language, page))
                .thenReturn(mockPeopleList)

            // When
            val result = getPopularPeopleUseCase(language, page)

            // Then
            assertEquals(mockPeopleList, result)
        }
    }
}