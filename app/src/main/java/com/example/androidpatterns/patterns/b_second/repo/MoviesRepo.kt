package com.example.androidpatterns.patterns.b_second.repo

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import com.example.androidpatterns.patterns.b_second.entity.Genre
import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.entity.MovieFabric
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

class IMDB(config: AppConfig) {
    private val networkManager = NetworkManager(Logger, config)
    private val movieFabric = MovieFabric()
    private val movies = mutableListOf(
        movieFabric.createImdbMovie(
            1,
            "Sherlock",
            2000,
            Genre.DETECTIVE
        ),
        movieFabric.createImdbMovie(
            2,
            "Ghost hunters",
            2005,
            Genre.FAMILY
        ),
        movieFabric.createImdbMovie(
            3,
            "Spanch Bob",
            2019,
            Genre.CARTOON
        ),
        movieFabric.createImdbMovie(
            4,
            "Wheel",
            2019,
            Genre.DETECTIVE
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return if (networkManager.isNetworkAvailable()) {
            movies.filter { it.name == name }
        } else {
            emptyList()
        }
    }

    fun getListOfMoviesByGenre(genre: Genre): List<Movie> {
        return if (networkManager.isNetworkAvailable()) {
            movies.filter { it.genre == genre }
        } else {
            emptyList()
        }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return if (networkManager.isNetworkAvailable()) {
            movies.filter { it.year >= year }
        } else {
            emptyList()
        }
    }
}

class Kinopoisk(config: AppConfig) {
    private val networkManager = NetworkManager(Logger, config)
    private val movieFabric = MovieFabric()
    private val movies = mutableListOf(
        movieFabric.createKinopoiskMovie(
            1,
            "Sherlock",
            2000,
            Genre.DETECTIVE
        ),
        movieFabric.createKinopoiskMovie(
            2,
            "Joker",
            2019,
            Genre.DRAMA
        ),
        movieFabric.createKinopoiskMovie(
            3,
            "Spider Man",
            2005,
            Genre.FANTASY
        ),
        movieFabric.createKinopoiskMovie(
            4,
            "Sprinter",
            2005,
            Genre.DRAMA
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        return if (networkManager.isNetworkAvailable()) {
            movies.filter { it.name == name }
        } else {
            emptyList()
        }
    }

    fun getListOfMoviesByGenre(genre: Genre): List<Movie> {
        return if (networkManager.isNetworkAvailable()) {
            movies.filter { it.genre == genre }
        } else {
            emptyList()
        }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return if (networkManager.isNetworkAvailable()) {
            movies.filter { it.year >= year }
        } else {
            emptyList()
        }
    }
}

class Repository(config: AppConfig) {
    private val imdbServer = IMDB(config)
    private val kinopoiskServer = Kinopoisk(config)

    fun getMovieByName(name: String): List<Movie> {
        Logger.printLog("Repository", "getMovieByName: was called with params : name [$name]")

        var movie = imdbServer.getMovieByName(name)
        var server = "IMDB"
        if (movie.isEmpty()) {
            movie = kinopoiskServer.getMovieByName(name)
            server = "Kinopoisk"
        }
        Logger.printLog("Repository", "getMovieByName: return movie $movie from server $server")
        return movie
    }

    fun getMoviesByGenre(genre: Genre): MutableList<Movie> {
        Logger.printLog("Repository", "getMovieByGenre: was called with params : name [$genre]")
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies, "getMoviesByGenre")
    }

    fun getMoviesNewestThan(year: Int): MutableList<Movie> {
        Logger.printLog("Repository", "getMoviesNewestThan: was called with params : name [$year]")
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
        Logger.printLog(
            "Repository",
            "$funcName: return movie $movies from IMDB & Kinopoisk servers"
        )
        return movies
    }
}