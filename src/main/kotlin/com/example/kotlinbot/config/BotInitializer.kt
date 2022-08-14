package com.example.kotlinbot.config

import com.example.kotlinbot.service.Bot
import com.example.kotlinbot.util.logger
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Component
class BotInitializer(
    private val bot: Bot
) {

    @EventListener(ContextRefreshedEvent::class)
    @Throws(TelegramApiException::class)
    fun init() {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        try {
            telegramBotsApi.registerBot(bot)
            LOG.info("Bot $bot is registered")
        } catch (e: TelegramApiRequestException) {
            LOG.error(" Bot $bot who's not registered");
        }
    }

    companion object {
        val LOG by logger()
    }
}