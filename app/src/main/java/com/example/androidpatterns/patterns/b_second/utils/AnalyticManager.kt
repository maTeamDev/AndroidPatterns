package com.example.androidpatterns.patterns.b_second.utils


class AnalyticManager {
    val logger = Logger()
    val networkManager = NetworkManager(logger)
    fun trackUserEvent(event: String, parameter: String) {
        if (networkManager.isNetworkAvailiable()) {
            logger.printLog("AnalyticManager",
                "event was sent to server using $parameter")
            sendEventToServer(event)
        }
        else logger.printLog("AnalyticManager", "no internet, event wasn't sent")
    }

    private fun sendEventToServer(event: String) {
        println("AnalyticManager: Sending event to server: $event")
    }
}