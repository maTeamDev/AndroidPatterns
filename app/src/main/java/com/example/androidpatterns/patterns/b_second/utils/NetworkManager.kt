package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import kotlin.random.Random

class NetworkManager(private val logger: Logger, private val config: AppConfig) {
    fun isNetworkAvailiable(): Boolean {
        val isNetworkAvailable = Random.nextBoolean()
        logger.printLog("NetworkManager", "isNetworkAvailable [$isNetworkAvailable]")
        return if(config.isNetworkPermitted) {
            isNetworkAvailable
        } else {
            println("isNetworkAvailable [FALSE]")
            false
        }
    }
}