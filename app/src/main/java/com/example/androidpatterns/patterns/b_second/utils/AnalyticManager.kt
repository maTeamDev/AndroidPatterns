package com.example.androidpatterns.patterns.b_second.utils


class AnalyticManager {
    private val logger = Logger()
    private val networkManager = NetworkManager(logger)

    fun trackUserEvent(event: String) {
        val loggerTag = "AnalyticManager.trackUserEvent()"

        if (networkManager.isNetworkAvailiable()) {
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