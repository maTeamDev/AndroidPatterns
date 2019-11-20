package com.example.androidpatterns.patterns.b_second.utils

import java.util.*

class Logger {
        fun printLog(tag:String, message:String, isLoggingOn:Boolean){
            if(isLoggingOn) {
                println("devLog: ${Date()} $tag: $message")
            }
    }
}