package com.example.androidpatterns.patterns.b_second.utils

import java.util.*

class Logger {
        fun printLog(tag:String, message:String){
        println("devLog: ${Date()} $tag: $message")
    }
}