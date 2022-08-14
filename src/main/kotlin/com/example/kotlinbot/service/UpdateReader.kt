package com.example.kotlinbot.service

import com.example.kotlinbot.enums.Commands
import com.example.kotlinbot.enums.TopicNames
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage.SendMessageBuilder
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UpdateReader(
    private val topicService: TopicService,
    private val keyboardService: KeyboardService
) {
    private var builder = SendMessage.builder()


    fun readUpdate(update: Update): SendMessageBuilder? {
        if (update.hasMessage() && topicService.getCurrentTopic() == "null") {
            builder.chatId(update.message.chatId.toString())
            builder.replyMarkup(keyboardService.getTopicsInlineKeyboard(true))
        }
        if (update.hasMessage() && topicService.getCurrentTopic() != "null") {
            builder.chatId(update.message.chatId.toString())
        }
        if (update.hasCallbackQuery() && update.callbackQuery.data == TopicNames.CORE_ONE.name) {
            builder.chatId(update.callbackQuery.message.chatId.toString())
            builder.replyMarkup(keyboardService.getTopicsInlineKeyboard(false))
            return builder
        }
        if (update.hasCallbackQuery() && update.callbackQuery.data == Commands.RESTART.name) {
            builder.chatId(update.callbackQuery.message.chatId.toString())
            topicService.initializeTopic("null")
            builder.replyMarkup(keyboardService.getTopicsInlineKeyboard(true))
            return builder
        }
        if (update.hasCallbackQuery()) {
            builder.chatId(update.callbackQuery.message.chatId.toString())
            builder.replyMarkup(keyboardService.restartInlineKeyboard())
        }
        return builder
    }

}