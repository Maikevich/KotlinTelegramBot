package com.example.kotlinbot.service

import com.example.kotlinbot.entity.User
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UserService {

    private var users: MutableMap<Long, User> = hashMapOf()

    fun getUserByChatId(update: Update): User? {
        if (update.hasMessage()) {

            if (users.containsKey(update.message.from.id)) {
                return users[update.message.from.id]
            }
            addNewUser(update.message.from.id)
            return users[update.message.from.id]
        }

            return users[update.callbackQuery.from.id]
    }


    fun updateUser(user: User) {
        users.set(user.getChatId(), user)
    }

    private fun addNewUser(id: Long) {
        val mainTopic = MainTopic()
        var user = User(id, mainTopic)
        users[id] = user
    }

}