package com.example.kotlinbot.service

import com.example.kotlinbot.service.files.FileReader
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val mainTopic: MainTopic,
    private val fileReader: FileReader
) {

    fun getCurrentTopicAnswer(messageText: String): String {
        mainTopic.setAnswerFile(messageText)
        var result: String? = ""
        try {
            result = fileReader.readFile(mainTopic.getAnswersList())
        } catch (nfe: NumberFormatException) {
            println(nfe.message)
        }
        return result!!
    }

    fun getCurrentTopicQuestionsList(): String? = fileReader.readFile(mainTopic.getQuestionsFile())



    fun initializeTopic(str: String) {
        mainTopic.setTopic(str)
        mainTopic.setQuestionsFile()

    }

    fun getCurrentTopic() = mainTopic.getTopic()
}