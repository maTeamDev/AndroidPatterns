package com.example.androidpatterns.patterns.b_second.di

import com.example.androidpatterns.patterns.b_second.config.AppConfig
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