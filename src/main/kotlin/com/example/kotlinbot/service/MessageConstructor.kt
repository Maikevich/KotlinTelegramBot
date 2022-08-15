package com.example.kotlinbot.service

import com.example.kotlinbot.entity.User
import com.example.kotlinbot.enums.Commands
import com.example.kotlinbot.enums.TopicNames
import com.example.kotlinbot.service.files.FileReader
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class MessageConstructor(
    private val topicService: TopicService,
    private val fileReader: FileReader
) {

    private  var message: String = "Выберите тему"

    fun constructMessage(update: Update,user: User?): String {
        if ((user?.getMainTopic()?.getTopic() == "null" && update.hasMessage()) ||
            ( update.hasCallbackQuery() && update.callbackQuery.data == Commands.RESTART.name)
        ) {
           return message
        }

        if (user?.getMainTopic()?.getTopic() == "null" && update.hasCallbackQuery()) {
            if (update.callbackQuery.data == TopicNames.CORE_ONE.name) {
                return message
            }
            topicService.initializeTopic(update.callbackQuery.data,user)
           return topicService.getCurrentTopicQuestionsList(user).toString()
        }

        if (update.hasMessage() && user?.getMainTopic()?.getTopic() != "null") {
            user?.getMainTopic()?.setAnswerFile(update.message.text)
            try {
                var answer: String? = user?.getMainTopic()?.getAnswerFile()?.let { fileReader.readFile(it) }
                return answer!!
            } catch (nfe: NumberFormatException) {
                TopicService.LOG.error("Такого файла нет")
                return "Такого файла нет"
            }
        }

        return message

    }


}