package com.dfavilav.marvelapplication.data.remote

import com.dfavilav.marvelapplication.domain.model.Thumbnail
import com.dfavilav.marvelapplication.domain.model.comic.ApiResponse
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import com.dfavilav.marvelapplication.domain.model.hero.HeroApiResponse

class FakeMarvelApi: MarvelApi {

    private val heroes = listOf(
        Hero(
            id = 1,
            name = "Rick Sanchez",
            description = "",
            thumbnail = Thumbnail("", "")
        ),
        Hero(
            id = 2,
            name = "Rick Sanchez",
            description = "",
            thumbnail = Thumbnail("", "")
        ),
        Hero(
            id = 3,
            name = "Rick Sanchez",
            description = "",
            thumbnail = Thumbnail("", "")
        )
    )

    private fun findHeroes(name: String): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (name.isNotEmpty()) {
            heroes.forEach { hero ->
                if (hero.name.lowercase().contains(name.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else {
            emptyList()
        }
    }

    override suspend fun getAllHeroes(
        limit: Int,
        offset: Int,
        timeStamp: Int,
        apiKey: String,
        hash: String
    ): HeroApiResponse {
        return HeroApiResponse()
    }

    override suspend fun searchHeroes(
        name: String,
        limit: Int,
        offset: Int,
        timeStamp: Int,
        apiKey: String,
        hash: String
    ): HeroApiResponse {
        val searchedHeroes = findHeroes(name = name)
        return HeroApiResponse()
    }

    override suspend fun getAllComicsByCharacter(
        characterId: Int,
        limit: Int,
        offset: Int,
        timeStamp: Int,
        apiKey: String,
        hash: String
    ): ApiResponse {
        return ApiResponse()
    }
}
