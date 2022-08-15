package com.example.kotlinbot.service

import com.example.kotlinbot.service.files.FileReader
import com.example.kotlinbot.util.logger
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val mainTopic: MainTopic,
    private val fileReader: FileReader
) {

    fun getCurrentTopicAnswer(messageText: String): String {
        mainTopic.setAnswerFile(messageText)
        var result: String = "Введите номер вопроса из списка"
        try {
            var answer :String?  = fileReader.readFile(mainTopic.getAnswerFile())
            return answer!!
        } catch (nfe: NumberFormatException) {
           LOG.error("Не найден файл ответа")
        }
        return result
    }

    fun getCurrentTopicQuestionsList(): String? = fileReader.readFile(mainTopic.getQuestionsFile())



    fun initializeTopic(str: String) {
        mainTopic.setTopic(str)
        mainTopic.setQuestionsFile()

    }

    fun getCurrentTopic() = mainTopic.getTopic()

    companion object{
        val LOG by logger()
    }
}