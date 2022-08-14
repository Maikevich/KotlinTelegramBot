package com.example.kotlinbot.service

import com.example.kotlinbot.enums.Commands
import com.example.kotlinbot.enums.TopicNames
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class MessageConstructor(
    private val topicService: TopicService
) {

    private lateinit var message: String

    fun constructMessage(update: Update): String {
        if ((topicService.getCurrentTopic() == "null" && update.hasMessage()) ||
            ( update.hasCallbackQuery() && update.callbackQuery.data == Commands.RESTART.name)
        ) {
            message = "Выберите тему"
        }

        if (topicService.getCurrentTopic() == "null" && update.hasCallbackQuery()) {
            if (update.callbackQuery.data == TopicNames.CORE_ONE.name) {
                return message
            }
            topicService.initializeTopic(update.callbackQuery.data)
            message = topicService.getCurrentTopicQuestionsList().toString()
        }

        if (update.hasMessage() && topicService.getCurrentTopic() != "null") {
            message = topicService.getCurrentTopicAnswer(update.message.text)
        }

        return message

    }


}