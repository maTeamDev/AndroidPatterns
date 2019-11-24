package com.example.androidpatterns.patterns.b_second

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import com.example.androidpatterns.patterns.b_second.di.DIManager
import com.example.androidpatterns.patterns.b_second.entity.Movie
import com.example.androidpatterns.patterns.b_second.repo.Genre
import com.example.androidpatterns.patterns.b_second.repo.Repository
import com.example.androidpatterns.patterns.b_second.utils.Logger


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
 *
 * 1) применить паттерн Фабрика для создания обектов Movie.
 * 2) Создать ENUM  class Genre, в нём перечислить варианты и заменить Movie.genre стринг на enum
 *
 * * Seconds part:
 *
 * 5) I created AppConfig class and added it as a primary constructor parameter to the MoviesFinderApp class.
 *    Please use config object in order to achieve following goals:
 *     - enable/disable all logs in the app
 *     - enable/disable networking in the app.
 *
 * 6) Let's reduce memory allocation  of our app. Currently we have 4 Logger instances creation in the code.
 *     - Please refactor the code in order to reduce amount of Logger instances to 1
 *
 **/

fun main() {
    val config = AppConfig(isLoggingOn = true, isNetworkPermitted = true)
    val logger = Logger()
    DIManager.initialize(logger, config)

    val moviesFinder = MoviesFinderApp()
    moviesFinder.showMovieByName("Ghost hunters")
}


class MoviesFinderApp {
    private val repository = Repository()
    private val analyticManager = DIManager.analyticManager()

    fun showMovieByName(name: String) {
        analyticManager.trackUserEvent("showMovieByName", name)
        val movie = repository.getMovieByName(name)
        showInfo(movie)
    }

    fun showMoviesByGenre(genre: Genre) {
        analyticManager.trackUserEvent("showMoviesByGenre", genre.toString())
        var movies = repository.getMoviesByGenre(genre)
        movies = removeDuplicatesMovies(movies)
        showInfo(movies)
    }

    fun showMoviesNewestThan(year: Int) {
        analyticManager.trackUserEvent("showMoviesNewestThan", year.toString())
        var movies = repository.getMoviesNewestThan(year)
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