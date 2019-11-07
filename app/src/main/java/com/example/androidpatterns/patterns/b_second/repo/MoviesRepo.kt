package com.example.androidpatterns.patterns.b_second.repo

import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

class IMDB {
    val logger = Logger()
    val networkManager = NetworkManager(logger)
    private val movies = mutableListOf(
        Movie(
            1,
            "Sherlock",
            2000,
            "Detective"
            , "imdb",
            System.currentTimeMillis()
        ),
        Movie(
            2,
            "Ghost hunters",
            2005,
            "Family"
            , "imdb",
            System.currentTimeMillis()
        ),
        Movie(
            3,
            "Spanch Bob",
            2019,
            "Cartoon"
            , "imdb",
            System.currentTimeMillis()
        ),
        Movie(
            4,
            "Wheel",
            2019,
            "Detective"
            , "imdb",
            System.currentTimeMillis()
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        var movies = movies.filter { it.name == name }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        var movies = movies.filter { it.genre == genre }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        var movies = movies.filter { it.year >= year }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }
}

class Kinopoisk {
    val logger = Logger()
    val networkManager = NetworkManager(logger)
    private val movies = mutableListOf(
        Movie(
            1,
            "Sherlock",
            2000,
            "Detective"
            , "Kinopoisk",
            System.currentTimeMillis()
        ),
        Movie(
            2,
            "Joker",
            2019,
            "Drama"
            , "Kinopoisk",
            System.currentTimeMillis()
        ),
        Movie(
            3,
            "Spider Man",
            2005,
            "Fantasy"
            , "Kinopoisk",
            System.currentTimeMillis()
        ),
        Movie(
            4,
            "Sprinter",
            2005,
            "Drama"
            , "Kinopoisk",
            System.currentTimeMillis()
        )
    )

    fun getMovieByName(name: String): List<Movie> {
        var movies = movies.filter { it.name == name }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        var movies = movies.filter { it.genre == genre }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        var movies = movies.filter { it.year >= year }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }
}

class Repository {
    private val imdbServer = IMDB()
    private val kinopoiskServer = Kinopoisk()
    private val logger = Logger()

    fun getMovieByName(name: String): List<Movie> {
        var movie = imdbServer.getMovieByName(name)
        var usedServer = "imdb"
        if (movie.isEmpty()) {
            usedServer = "kinopoisk"
            movie = kinopoiskServer.getMovieByName(name)
        }
        logger.printLog(
            "MoviesRepo",
            "method: getMovieByName " +
                    "argument: $name " +
                    "method return: $movie " +
                    "server: + $usedServer"
        )
        return movie
    }

    fun getMoviesByGenre(genre: String): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        logger.printLog(
            "MoviesRepo",
            "method: getMoviesByGenre " +
                    "argument: $genre " +
                    "method return: ${generateNewListOfMovies(imdbMovies, kinopoiskMovies)} "
        )
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    fun getMoviesNewestThan(year: Int): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfNewestMovies(year)
        val kinopoiskMovies = kinopoiskServer.getListOfNewestMovies(year)
        logger.printLog(
            "MoviesRepo",
            "method: getMoviesNewestThan " +
                    "argument: $year " +
                    "method return: ${generateNewListOfMovies(imdbMovies, kinopoiskMovies)} "
        )
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    private fun generateNewListOfMovies(
        imdbMovies: List<Movie>,
        kinopoiskMovies: List<Movie>
    ): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        movies.addAll(imdbMovies)
        movies.addAll(kinopoiskMovies)
        logger.printLog(
            "MoviesRepo",
            "method: generateNewListOfMovies "
        )
        return movies
    }
}