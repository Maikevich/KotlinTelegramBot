package com.example.kotlinbot.entity

import com.example.kotlinbot.service.MainTopic

class User(
    private var chatId: Long,
    private var mainTopic: MainTopic
) {
    fun getChatId() = chatId
    fun getMainTopic(): MainTopic = mainTopic

}