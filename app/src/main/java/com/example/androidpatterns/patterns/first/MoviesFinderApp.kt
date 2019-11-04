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
        repository.printMovieByName(name)
    }

    fun getMoviesByGenre(genre: String) {
        repository.getMoviesByGenre(genre)
    }

    fun getNewestMovies() {
        repository.getNewestMovies()
    }
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

    fun getMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre }
    }

    fun getNewestMovies(): List<Movie> {
        return movies.filter { it.year == 2019 }
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

    fun getMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre }
    }

    fun getNewestMovies(): List<Movie> {
        return movies.filter { it.year == 2019 }
    }
}

class Repository {
    private val imdbServer = IMDB()
    private val kinopoiskServer = Kinopoisk()

    fun printMovieByName(name: String) {
        var movie = imdbServer.findMovieByName(name)
        if (movie.isEmpty()) {
            movie = kinopoiskServer.findMovieByName(name)
        }
        if (movie.isEmpty()) {
            println("There is no movie")
        } else {
            println(movie)
        }
    }

    fun getMoviesByGenre(genre: String) {
        val imdbMovies = imdbServer.getMoviesByGenre(genre)
        val kinopoiskMovies = kinopoiskServer.getMoviesByGenre(genre)
        val movies = mutableListOf<Movie>()
        movies.addAll(imdbMovies)
        movies.addAll(kinopoiskMovies)
        println(movies.distinctBy { it.name })
    }

    fun getNewestMovies() {
        val imdbMovies = imdbServer.getNewestMovies()
        val kinopoiskMovies = kinopoiskServer.getNewestMovies()
        val movie = mutableListOf<Movie>()
        movie.addAll(imdbMovies)
        movie.addAll(kinopoiskMovies)
        println(movie.distinctBy { it.name })
    }
}