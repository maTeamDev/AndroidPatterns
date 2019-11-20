package com.example.androidpatterns.patterns.b_second.entity

class MovieFabric {
    private fun createMovie(
        id: Int,
        name: String,
        year: Int,
        genre: Genre,
        serverName: String
    ): Movie {
        return Movie(
            id,
            name,
            year,
            genre,
            serverName,
            System.currentTimeMillis()
        )
    }

    fun createImdbMovie(
        id: Int,
        name: String,
        year: Int,
        genre: Genre
    ): Movie {
        return createMovie(id, name, year, genre, "IMDB")
    }

    fun createKinopoiskMovie(
        id: Int,
        name: String,
        year: Int,
        genre: Genre
    ): Movie {
        return createMovie(id, name, year, genre, "Kinopoisk")
    }
}

enum class Genre {
    DETECTIVE,
    FAMILY,
    CARTOON,
    DRAMA,
    FANTASY,
}