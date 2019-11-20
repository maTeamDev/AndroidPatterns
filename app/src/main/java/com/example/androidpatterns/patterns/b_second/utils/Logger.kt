package com.example.androidpatterns.patterns.b_second.utils

import com.example.androidpatterns.patterns.b_second.config.AppConfig
import java.util.*

/*class Logger(private val config: AppConfig) {
    fun printLog(tag: String, message: String) {
        if (config.isLoggingOn)
            println("devLog: ${Date()} $tag: $message")
    }
}*/

object Logger {

    var config: AppConfig? = null

    fun printLog(tag: String, message: String) {
        if (config?.isLoggingOn!!)
            println("devLog: ${Date()} $tag: $message")
    }
}
