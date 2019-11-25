package com.example.androidpatterns.patterns.b_second.di

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import com.example.androidpatterns.patterns.b_second.repo.IMDB
import com.example.androidpatterns.patterns.b_second.repo.Kinopoisk
import com.example.androidpatterns.patterns.b_second.repo.Repository
import com.example.androidpatterns.patterns.b_second.utils.AnalyticManager
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

object DIManager {
    private var logger: Logger? = null
    private var config: AppConfig? = null
    private var networkManager: NetworkManager? = null
    private var analyticManager: AnalyticManager? = null
    private var repository: Repository? = null
    private var imdb: IMDB? = null
    private var kinopoisk: Kinopoisk? = null

    fun initialize(appConfig: AppConfig) {
        logger = Logger()
        config = appConfig
    }

    fun getLogger(): Logger {
        return logger as Logger
    }

    fun getConfig(): AppConfig {
        return config as AppConfig
    }

    fun getRepo(): Repository {
        if (repository == null) {
            repository = Repository()
        }
        return repository as Repository
    }

    fun getIMDB(): IMDB {
        if (imdb == null) {
            imdb = IMDB()
        }
        return imdb as IMDB
    }

    fun getKinopoisk(): Kinopoisk {
        if (kinopoisk == null) {
            kinopoisk = Kinopoisk()
        }
        return kinopoisk as Kinopoisk
    }

    fun getNetworkManager(): NetworkManager {
        if (networkManager == null) {
            networkManager = NetworkManager()
        }
        return networkManager as NetworkManager
    }

    fun getAnalyticManager(): AnalyticManager {
        if (analyticManager == null) {
            analyticManager = AnalyticManager()
        }
        return analyticManager as AnalyticManager
    }
}