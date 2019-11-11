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
            emptyList()
        }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
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
            emptyList()
        }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
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

    fun getMoviesByGenre(genre: String): MutableList<Movie> {
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
        logger.printLog("Repository", "$funcName: return movie $movies from IMDB & Kinopoisk servers")
        return movies
    }
}