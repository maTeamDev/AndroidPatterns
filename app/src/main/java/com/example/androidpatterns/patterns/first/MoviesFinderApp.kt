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

    fun getMoviesByGenre (genre: String){
        repository.getMoviesByGenre(genre)
    }

    fun getNewestMovies() {
        repository.getNewestMovies()
    }
}
 /*Remote: Permission to maTeamDev/AndroidPatterns.git denied to Shymakher.
 unable to access 'https://github.com/maTeamDev/AndroidPatterns/':
 The requested URL returned error: 403*/


fun main() {
    val moviesFinder = MoviesFinderApp()
    moviesFinder.printMovieByName("Joker2")
    moviesFinder.getMoviesByGenre("Detective")
//    moviesFinder.getNewestMovies()
}

data class Movie(val id: Int, val name: String, val year: Int, val genre: String)

class IMDB {
    val movies = mutableListOf(
        Movie(1, "Sherlock", 2000, "Detective"),
        Movie(2, "Ghost hunters", 2005, "Family"),
        Movie(3, "Spanch Bob", 2019, "Cartoon"),
        Movie(4, "Wheel", 2019, "Detective")
    )
}

class Kinopoisk {
    val movies = mutableListOf(
        Movie(1, "Sherlock", 2000, "Detective"),
        Movie(2, "Joker", 2019, "Drama"),
        Movie(3, "Spider Man", 2005, "Fantasy"),
        Movie(4, "Sprinter", 2005, "Drama")
    )
}

class Repository {
    private val imdb = IMDB()
    private val kinopoisk = Kinopoisk()

    fun printMovieByName(name: String) {
        var result = imdb.movies.filter { it.name == name }
        if(result.isEmpty()){
            result =  kinopoisk.movies.filter{ it.name == name }
        }
        if(result.isEmpty()){
            println("There is no movie")
        } else {
            println(result)
        }

    }

    fun getMoviesByGenre (genre: String){
        val  resultIMDB = imdb.movies.filter { it.genre == genre}
        val resultKinopoisk = kinopoisk.movies.filter { it.genre == genre}
        val result = mutableListOf<Movie>()
        result.addAll(resultIMDB)
        result.addAll(resultKinopoisk)
        println(result.distinct())
    }

    fun getNewestMovies() {
        val  resultIMDB = imdb.movies.filter { it.year == 2019}
        val resultKinopoisk = kinopoisk.movies.filter { it.year == 2019}
        val result = mutableListOf<Movie>()
        result.addAll(resultIMDB)
        result.addAll(resultKinopoisk)
        println(result.distinct())
    }
}