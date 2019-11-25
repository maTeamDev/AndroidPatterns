package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.di.DIManager

class AnalyticManager {
    private val networkManager = DIManager.getNetworkManager()
    private val logger = DIManager.getLogger()
    private val config = DIManager.getConfig()
    fun trackUserEvent(event: String, parameter: String) {
        if (networkManager.isNetworkAvailable()) {
            logger.printLog(
                "AnalyticManager",
                "event was sent to server using $parameter", config.isLoggingOn
            )
            sendEventToServer(event)
        } else logger.printLog(
            "AnalyticManager", "no internet, event wasn't sent",
            config.isLoggingOn
        )
    }

    private fun sendEventToServer(event: String) {
        println("AnalyticManager: Sending event to server: $event")
    }
}