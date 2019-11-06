package com.example.androidpatterns.patterns.b_second.utils

import kotlin.random.Random

class NetworkManager(private val logger: Logger) {
    fun isNetworkAvailiable(): Boolean {
        val isNetworkAvailable = Random.nextBoolean()
        logger.printLog("NetworkManager", "isNetworkAvailable [$isNetworkAvailable]")
        return isNetworkAvailable
    }
}