package com.example.androidpatterns.patterns.b_second.utils


class AnalyticManager {
    fun trackUserEvent(event: String) {
        TODO("Check is internet available using NetworkManager class")
        TODO("Print log using Logger class that says if event was sent to server or event can not be sent becouse of internet")
        TODO("if network available - Send event to server using method I prepared")
    }

    private fun sendEventToServer(event: String) {
        println("AnalyticManager: Sending event to server: $event")
    }
}