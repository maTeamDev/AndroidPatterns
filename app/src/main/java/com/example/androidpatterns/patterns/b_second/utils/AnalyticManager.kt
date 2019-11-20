package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.config.AppConfig


class AnalyticManager(config: AppConfig) {
    private val logger = Logger(config)
    private val networkManager = NetworkManager(logger, config)

    fun trackUserEvent(event: String) {
        val loggerTag = "AnalyticManager.trackUserEvent()"

        if (networkManager.isNetworkAvailable()) {
            sendEventToServer(event)
            logger.printLog(
                loggerTag,
                "Event $event was send to Server"
            )
        } else {
            logger.printLog(
                loggerTag,
                "Event $event was not send to Server because of internet connection"
            )
        }
    }

    private fun sendEventToServer(event: String) {
        println("AnalyticManager: Sending event to server: $event")
    }
}