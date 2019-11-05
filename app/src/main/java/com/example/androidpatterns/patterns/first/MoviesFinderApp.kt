package com.example.androidpatterns.patterns.first


/**
 * In this task we will developed MoviesFinderApp that will search for a movies from two different servers and print search results to console
 *
 * 1) You need to create two different classes (IMDB and Kinopoisk). Each of them should represent the movies server and contains a set of unique movies
 * 2) The movie should have next params: id (random and unique), name, year, genre
 * 3) Each server should contain next functions:
 * - find movie by name in both servers
 * - get all movies by specific genre from both servers
 * - get all movies newest or equals to passed year
 **/


/**
 * Use this class as a start point
 */
class MoviesFinderApp {
    private val repository = Repository()

    fun printMovieByName(name: String) {
        val movie = repository.findMovieByName(name)
        showInfo(movie)
    }

    fun getMoviesByGenre(genre: String) {
        var movies = repository.getListOfMoviesByGenre(genre)
        movies = removeDuplicatesMovies(movies)
        showInfo(movies)
    }

    fun getNewestMovies(year: Int) {
        var movies = repository.getListOfNewestMovies(year)
        movies = removeDuplicatesMovies(movies)
        showInfo(movies)
    }
}

fun removeDuplicatesMovies(movies: List<Movie>): MutableList<Movie> {
    return movies.distinctBy { it.name }.toMutableList()
}

fun showInfo(data: Any) {
    println(data)
}

fun main() {
    val moviesFinder = MoviesFinderApp()
    moviesFinder.printMovieByName("Joker")
}

data class Movie(val id: Int, val name: String, val year: Int, val genre: String)

class IMDB {
    private val movies = mutableListOf(
        Movie(1, "Sherlock", 2000, "Detective"),
        Movie(2, "Ghost hunters", 2005, "Family"),
        Movie(3, "Spanch Bob", 2019, "Cartoon"),
        Movie(4, "Wheel", 2019, "Detective")
    )

    fun findMovieByName(name: String): List<Movie> {
        return movies.filter { it.name == name }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return movies.filter { it.year == year }
    }
}

class Kinopoisk {
    private val movies = mutableListOf(
        Movie(1, "Sherlock", 2000, "Detective"),
        Movie(2, "Joker", 2019, "Drama"),
        Movie(3, "Spider Man", 2005, "Fantasy"),
        Movie(4, "Sprinter", 2005, "Drama")
    )

    fun findMovieByName(name: String): List<Movie> {
        return movies.filter { it.name == name }
    }

    fun getListOfMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre }
    }

    fun getListOfNewestMovies(year: Int): List<Movie> {
        return movies.filter { it.year == year }
    }
}

class Repository {
    private val imdbServer = IMDB()
    private val kinopoiskServer = Kinopoisk()

    fun findMovieByName(name: String): List<Movie> {
        var movie = imdbServer.findMovieByName(name)
        if (movie.isEmpty()) {
            movie = kinopoiskServer.findMovieByName(name)
        }
        return movie
    }

    fun getListOfMoviesByGenre(genre: String): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getListOfMoviesByGenre(genre)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    fun getListOfNewestMovies(year: Int): MutableList<Movie> {
        val imdbMovies = imdbServer.getListOfNewestMovies(year)
        val kinopoiskMovies = kinopoiskServer.getListOfNewestMovies(year)
        return generateNewListOfMovies(imdbMovies, kinopoiskMovies)
    }

    private fun generateNewListOfMovies(imdbMovies: List<Movie>, kinopoiskMovies: List<Movie>): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        movies.addAll(imdbMovies)
        movies.addAll(kinopoiskMovies)
        return movies
    }
}