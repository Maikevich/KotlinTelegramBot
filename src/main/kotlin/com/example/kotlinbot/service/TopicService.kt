package com.example.kotlinbot.service

import com.example.kotlinbot.entity.User
import com.example.kotlinbot.service.files.FileReader
import com.example.kotlinbot.util.logger
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val mainTopic: MainTopic,
    private val fileReader: FileReader,
    private val userService: UserService
) {



    fun getCurrentTopicQuestionsList(user:User?): String? {
       return user?.getMainTopic()?.getQuestionsFile()?.let { fileReader.readFile(it) }
    }


    fun initializeTopic(str: String, user: User) {
        user?.getMainTopic()?.setTopic(str)
        user?.getMainTopic()?.setQuestionsFile()
        userService.updateUser(user)
    }

    fun getCurrentTopic() = mainTopic.getTopic()

    companion object {
        val LOG by logger()
    }
}