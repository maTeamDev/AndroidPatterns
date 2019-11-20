package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.config.AppConfig


class AnalyticManager(private val config: AppConfig, private val logger: Logger) {
    val networkManager = NetworkManager(config, logger)
    fun trackUserEvent(event: String, parameter: String) {
        if (networkManager.isNetworkAvailiable()) {
            logger.printLog("AnalyticManager",
                "event was sent to server using $parameter", config.isLoggingOn)
            sendEventToServer(event)
        }
        else logger.printLog("AnalyticManager", "no internet, event wasn't sent",
            config.isLoggingOn)
    }

    private fun sendEventToServer(event: String) {
        println("AnalyticManager: Sending event to server: $event")
    }
}