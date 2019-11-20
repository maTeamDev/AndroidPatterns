package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.config.AppConfig


class AnalyticManager(config: AppConfig) {
    private val networkManager = NetworkManager(Logger, config)

    fun trackUserEvent(event: String) {
        val loggerTag = "AnalyticManager.trackUserEvent()"

        if (networkManager.isNetworkAvailable()) {
            sendEventToServer(event)
            Logger.printLog(
                loggerTag,
                "Event $event was send to Server"
            )
        } else {
            Logger.printLog(
                loggerTag,
                "Event $event was not send to Server because of internet connection"
            )
        }
    }

    private fun sendEventToServer(event: String) {
        println("AnalyticManager: Sending event to server: $event")
    }
}