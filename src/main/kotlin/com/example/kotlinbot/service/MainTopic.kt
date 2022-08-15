package com.example.kotlinbot.service

import org.springframework.stereotype.Component

@Component
  class MainTopic() {
    private var topic: String = "null"
    private lateinit var answerFile: String
    private lateinit var questionsFile: String

    fun setTopic(topic: String) {
        this.topic = topic
    }

    fun getTopic() = topic

    fun getQuestionsFile() = questionsFile

    fun setQuestionsFile() {
        questionsFile = "files/questions/$topic.txt"
    }

    fun setAnswerFile(messageText: String) {
        answerFile ="files/answers/$topic/$messageText.txt"
    }

    fun getAnswerFile() = answerFile
}