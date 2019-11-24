package com.example.androidpatterns.patterns.b_second.di

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import com.example.androidpatterns.patterns.b_second.utils.AnalyticManager
import com.example.androidpatterns.patterns.b_second.utils.Logger
import com.example.androidpatterns.patterns.b_second.utils.NetworkManager

object DIManager {
    private var logger: Logger? = null
    private var config: AppConfig? = null

    fun initialize(appLogger: Logger, appConfig: AppConfig) {
        logger = appLogger
        config = appConfig
    }

    fun getLogger(): Logger {
        return logger as Logger
    }

    fun getConfig(): AppConfig {
        return config as AppConfig
    }

    fun networkManager(): NetworkManager {
        return NetworkManager(getConfig(), getLogger())
    }

    fun analyticManager(): AnalyticManager {
        return AnalyticManager(getConfig(), getLogger())
    }
}