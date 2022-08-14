package com.example.kotlinbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig(
    @Value("\${botUserName}") private val botUserName: String,
    @Value("\${token}") private val token: String
) {
    fun getBotToken() = token
    fun getBotUserName() = botUserName

}