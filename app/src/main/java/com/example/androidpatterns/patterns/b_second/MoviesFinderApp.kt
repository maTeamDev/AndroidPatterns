package com.example.androidpatterns.patterns.b_second

import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.repo.Repository
import com.example.androidpatterns.patterns.b_second.utils.AnalyticManager


/**
 * In this task we are going to extend our application and make it look a little bit closer to reality.
 *
 * 1) I want you to start using Logger class. I prepared it for you. You can find it in a utils package.
 *    Please update existing Repository in order to print logs that shows:
 *    - name of the method that was called
 *    - method arguments that were passed
 *    - what method return and from which dataSource (Server) it was taken
 *
 * 2) Add check if network available to each server using NetworkManager.
 *    -  if network is not  available - print log using Logger and return empty list
 *
 * 3) We will add another unit that exist in EVERY mobile application with real users  - AnalyticManager.
 *    - check AnalyticManager class and finish implementation. You are gonna need Logger and NetworkManager in order to do that.
 *    - update MoviesFinderApp class and track user activities in App by tracking which method were called and with which parameters.
 *
 * 4) Please update Movie class by adding two additional properties to it.
 *    - serverName:String (IMDB or Kinopoisk)
 *    - downloadTimestamp:Long (you can use Java SDK method System.currentTimeMillis()):
 **/

fun main() {
    val moviesFinder = MoviesFinderApp()
    moviesFinder.showMovieByName("Sherlock")
}


class MoviesFinderApp {
    private val repository = Repository()
    private val analyticManager = AnalyticManager()

    fun showMovieByName(name: String) {
        val movie = repository.getMovieByName(name)
        showInfo(movie)
        analyticManager.trackUserEvent("showMovieByName: was called with params: name [$name]")
    }

    fun showMoviesByGenre(genre: String) {
        var movies = repository.getMoviesByGenre(genre)
        movies = removeDuplicatesMovies(movies)
        showInfo(movies)
        analyticManager.trackUserEvent("showMoviesByGenre: was called with params: genre [$genre]")
    }

    fun showMoviesNewestThan(year: Int) {
        var movies = repository.getMoviesNewestThan(year)
        movies = removeDuplicatesMovies(movies)
        showInfo(movies)
        analyticManager.trackUserEvent("showMoviesNewestThan: was called with params: year [$year]")

    }
}

fun removeDuplicatesMovies(movies: List<Movie>): MutableList<Movie> {
    return movies.distinctBy { it.name }.toMutableList()
}

fun showInfo(data: Any) {
    println(data)
}