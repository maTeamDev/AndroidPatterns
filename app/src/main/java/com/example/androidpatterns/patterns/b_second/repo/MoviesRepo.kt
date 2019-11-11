package com.example.androidpatterns.patterns.b_second.repo

import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

fun createMovie(
    id: Int,
    name: String,
    year: Int,
    genre: Genre,
    serverName: String
): Movie {

    /*val genreEnum =
        when (genre) {
            "Detective" -> Genre.DETECTIVE
            "Drama" -> Genre.DRAMA
            "Cartoon" -> Genre.CARTOON
            "Comedy" -> Genre.COMEDY
            "Family" -> Genre.FAMILY
            "Fantasy" -> Genre.FANTASY
            else -> Genre.UNKNOWN
        }*/

    return Movie(
        id,
        name,
        year,
        genre,
        serverName,
        System.currentTimeMillis()
    )
}

enum class Genre {
    DETECTIVE,
    COMEDY,
    FAMILY,
    CARTOON,
    DRAMA,
    FANTASY,
    UNKNOWN
}

class IMDB {
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)
    private val movies = mutableListOf(
        createMovie(
            1,
            "Sherlock",
            2000,
            Genre.DETECTIVE,
            "IMDB"
        ),
        createMovie(
            2,
            "Ghost hunters",
            2005,
            Genre.FAMILY,
            "IMDB"
        ),
        createMovie(
            3,
            "Spanch Bob",
            2019,
            Genre.CARTOON,
            "IMDB"
        ),
        createMovie(
            4,
            "Wheel",
            2019,
            Genre.DETECTIVE,
            "IMDB"
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.name == name }
        } else {
            emptyList()
        }
    }

    fun getListOfMoviesByGenre(genre: Genre): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.genre == genre }
        } else {
            emptyList()
        }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.year >= year }
        } else {
            emptyList()
        }
    }
}

class Kinopoisk {
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)
    private val movies = mutableListOf(
        createMovie(
            1,
            "Sherlock",
            2000,
            Genre.DETECTIVE,
            "Kinopoisk"
        ),
        createMovie(
            2,
            "Joker",
            2019,
            Genre.DRAMA,
            "Kinopoisk"
        ),
        createMovie(
            3,
            "Spider Man",
            2005,
            Genre.FANTASY,
            "Kinopoisk"
        ),
        createMovie(
            4,
            "Sprinter",
            2005,
            Genre.DRAMA,
            "Kinopoisk"
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.name == name }
        } else {
            emptyList()
        }
    }

    fun getListOfMoviesByGenre(genre: Genre): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.genre == genre }
        } else {
            emptyList()
        }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.year >= year }
        } else {
            emptyList()
        }
    }
}

class Repository {
    private val imdbServer = IMDB()
    private val kinopoiskServer = Kinopoisk()
    private val logger = Logger()

    fun getMovieByName(name: String): List<Movie> {
        logger.printLog("Repository", "getMovieByName: was called with params : name [$name]")

        var movie = imdbServer.getMovieByName(name)
        var server = "IMDB"
        if (movie.isEmpty()) {
            movie = kinopoiskServer.getMovieByName(name)
            server = "Kinopoisk"
        }
        logger.printLog("Repository", "getMovieByName: return movie $movie from server $server")
        return movie
    }

    fun getMoviesByGenre(genre: Genre): MutableList<Movie> {
        logger.printLog("Repository", "getMovieByGenre: was called with params : name [$genre]")
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies, "getMoviesByGenre")
    }

    fun getMoviesNewestThan(year: Int): MutableList<Movie> {
        logger.printLog("Repository", "getMoviesNewestThan: was called with params : name [$year]")
        val imdbMovies = imdbServer.getListOfNewestMovies(year)
        val kinopoiskMovies = kinopoiskServer.getListOfNewestMovies(year)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies, "getMoviesNewestThan")
    }

    private fun generateNewListOfMovies(
        imdbMovies: List<Movie>,
        kinopoiskMovies: List<Movie>,
        funcName: String
    ): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        movies.addAll(imdbMovies)
        movies.addAll(kinopoiskMovies)
        logger.printLog(
            "Repository",
            "$funcName: return movie $movies from IMDB & Kinopoisk servers"
        )
        return movies
    }
}