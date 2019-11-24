package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import kotlin.random.Random

class NetworkManager(private val config: AppConfig, private val logger: Logger) {
    fun isNetworkAvailable(): Boolean {
        var isNetworkAvailable = false
        if (config.isNetworkPermitted) {
            isNetworkAvailable = Random.nextBoolean()
        }
        logger.printLog(
            "NetworkManager", "isNetworkAvailable [$isNetworkAvailable]",
            config.isLoggingOn
        )
        return isNetworkAvailable
    }
}