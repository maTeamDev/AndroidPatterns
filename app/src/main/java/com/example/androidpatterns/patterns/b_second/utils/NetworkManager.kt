package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.di.DIManager
import kotlin.random.Random

class NetworkManager {
    private val logger = DIManager.getLogger()
    private val config = DIManager.getConfig()
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