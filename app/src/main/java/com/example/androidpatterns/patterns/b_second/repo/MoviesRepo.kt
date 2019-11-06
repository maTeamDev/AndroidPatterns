package com.example.androidpatterns.patterns.b_second.repo

import com.example.androidpatterns.patterns.b_second.entity.Movie

class IMDB {
    private val movies = mutableListOf(
        Movie(
            1,
            "Sherlock",
            2000,
            "Detective"
        ),
        Movie(
            2,
            "Ghost hunters",
            2005,
            "Family"
        ),
        Movie(
            3,
            "Spanch Bob",
            2019,
            "Cartoon"
        ),
        Movie(
            4,
            "Wheel",
            2019,
            "Detective"
        )
    )

    fun findMovieByName(name: String): List<Movie> {
        return movies.filter { it.name == name }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return movies.filter { it.year >= year }
    }
}

class Kinopoisk {
    private val movies = mutableListOf(
        Movie(
            1,
            "Sherlock",
            2000,
            "Detective"
        ),
        Movie(
            2,
            "Joker",
            2019,
            "Drama"
        ),
        Movie(
            3,
            "Spider Man",
            2005,
            "Fantasy"
        ),
        Movie(
            4,
            "Sprinter",
            2005,
            "Drama"
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return movies.filter { it.name == name }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return movies.filter { it.year >= year }
    }
}

class Repository {
    private val imdbServer = IMDB()
    private val kinopoiskServer = Kinopoisk()

    fun getMovieByName(name: String): List<Movie> {
        var movie = imdbServer.findMovieByName(name)
        if (movie.isEmpty()) {
            movie = kinopoiskServer.getMovieByName(name)
        }
        return movie
    }

    fun getMoviesByGenre(genre: String): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    fun getMoviesNewestThan(year: Int): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfNewestMovies(year)
        val kinopoiskMovies = kinopoiskServer.getListOfNewestMovies(year)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    private fun generateNewListOfMovies(
        imdbMovies: List<Movie>,
        kinopoiskMovies: List<Movie>
    ): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        movies.addAll(imdbMovies)
        movies.addAll(kinopoiskMovies)
        return movies
    }
}