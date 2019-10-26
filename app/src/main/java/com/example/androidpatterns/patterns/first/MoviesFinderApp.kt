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

    fun printMovieByName(name: String) {
        TODO("Implementation")
    }
}


fun main() {
    val moviesFinder = MoviesFinderApp()
    moviesFinder.printMovieByName("Joker")
}






