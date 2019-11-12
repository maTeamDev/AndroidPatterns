package com.example.androidpatterns.patterns.b_second.repo

import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

enum class Genre{
    DRAMA,
    COMEDY,
    THRILLER
}

class MovieFactory(){

    fun createMovie(id: Int, name: String, year: Int, genre: Genre, serverName: String ): Movie{
        return Movie(id,
            name,
            year,
            genre,
            serverName,
            System.currentTimeMillis())
    }

    fun createMovieImdb(id: Int, name: String, year: Int, genre: Genre): Movie{
        return createMovie(id, name, year, genre, "Imdb")
    }

    fun createMovieKinopoisk(id: Int, name: String, year: Int, genre: Genre): Movie{
        return createMovie(id, name, year, genre, "Kinopoisk")
    }

}


class IMDB {
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)
    private val movieFactory = MovieFactory()
    private val movies = mutableListOf(
        movieFactory.createMovieImdb(1, "Sherlock", 2000, Genre.DRAMA),
        movieFactory.createMovieImdb(2, "Ghost hunters", 2019, Genre.COMEDY),
        movieFactory.createMovieImdb(3, "Spanch Bob", 2019, Genre.DRAMA),
        movieFactory.createMovieImdb(4, "Wheel", 2005, Genre.THRILLER)
    )

    fun getMovieByName(name: String): List<Movie> {
        var movies = movies.filter { it.name == name }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }

    fun getListOfMoviesByGenre(genre: Genre): List<Movie> {
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
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)
    private val movieFactory = MovieFactory()
    private val movies = mutableListOf(
        movieFactory.createMovieImdb(1, "Sherlock", 2000, Genre.DRAMA),
        movieFactory.createMovieImdb(2, "Ghost hunters", 2019, Genre.COMEDY),
        movieFactory.createMovieImdb(3, "Spanch Bob", 2019, Genre.DRAMA),
        movieFactory.createMovieImdb(4, "Wheel", 2005, Genre.THRILLER)
    )

    fun getMovieByName(name: String): List<Movie> {
        var movies = movies.filter { it.name == name }
        if (!networkManager.isNetworkAvailiable()) movies = listOf()
        return movies
    }

    fun getListOfMoviesByGenre(genre: Genre): List<Movie> {
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

    fun getMoviesByGenre(genre: Genre): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        val movies = generateNewListOfMovies(imdbMovies, kinopoiskMovies)
        logger.printLog(
            "MoviesRepo",
            "method: getMoviesByGenre " +
                    "argument: $genre " +
                    "method return: $movies} "
        )
        return movies
    }

    fun getMoviesNewestThan(year: Int): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfNewestMovies(year)
        val kinopoiskMovies = kinopoiskServer.getListOfNewestMovies(year)
        val movies = generateNewListOfMovies(imdbMovies, kinopoiskMovies)
        logger.printLog(
            "MoviesRepo",
            "method: getMoviesNewestThan " +
                    "argument: $year " +
                    "method return: $movies "
        )
        return movies
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