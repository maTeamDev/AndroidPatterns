package com.example.androidpatterns.patterns.b_second.repo

import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

class IMDB {
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)
    private val movies = mutableListOf(
        Movie(
            1,
            "Sherlock",
            2000,
            "Detective",
            "IMDB",
            1573045200000
        ),
        Movie(
            2,
            "Ghost hunters",
            2005,
            "Family",
            "IMDB",
            1573131600000
        ),
        Movie(
            3,
            "Spanch Bob",
            2019,
            "Cartoon",
            "IMDB",
            1573218000000
        ),
        Movie(
            4,
            "Wheel",
            2019,
            "Detective",
            "IMDB",
            1573304400000
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.name == name }
        } else {
            listOf()
        }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.genre == genre }
        } else {
            listOf()
        }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.year >= year }
        } else {
            listOf()
        }
    }
}

class Kinopoisk {
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)
    private val movies = mutableListOf(
        Movie(
            1,
            "Sherlock",
            2000,
            "Detective",
            "Kinopoisk",
            1573045200000
        ),
        Movie(
            2,
            "Joker",
            2019,
            "Drama",
            "Kinopoisk",
            1573045200000
        ),
        Movie(
            3,
            "Spider Man",
            2005,
            "Fantasy",
            "Kinopoisk",
            1573045200000
        ),
        Movie(
            4,
            "Sprinter",
            2005,
            "Drama",
            "Kinopoisk",
            1573045200000
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.name == name }
        } else {
            listOf()
        }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.genre == genre }
        } else {
            listOf()
        }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return if (networkManager.isNetworkAvailiable()) {
            movies.filter { it.year >= year }
        } else {
            listOf()
        }
    }
}

class Repository {
    private val imdbServer = IMDB()
    private val kinopoiskServer = Kinopoisk()
    private val logger = Logger()

    fun getMovieByName(name: String): List<Movie> {
        logger.printLog("method", "getMovieByName")
        logger.printLog("arguments", name)

        var movie = imdbServer.getMovieByName(name)
        var server = "IMDB"
        if (movie.isEmpty()) {
            movie = kinopoiskServer.getMovieByName(name)
            server = "Kinopoisk"
        }
        logger.printLog("dataSource & value", "$server, $movie")
        return movie
    }

    fun getMoviesByGenre(genre: String): MutableList<Movie> {
        logger.printLog("method", "getMoviesByGenre")
        logger.printLog("arguments", "genre")
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    fun getMoviesNewestThan(year: Int): MutableList<Movie> {
        logger.printLog("method", "getMoviesNewestThan")
        logger.printLog("arguments", "year")
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
        logger.printLog("dataSource & value", "IMDB & Kinopoisk, $movies")
        return movies
    }
}