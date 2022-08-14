package com.example.kotlinbot.service.keybord

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Service
class InlineKeyboardMarkupCreator {

    fun getInlineKeyboardMarkup(list: List<InlineKeyboardButton>): List<List<InlineKeyboardButton>>? {
        val rowList: MutableList<List<InlineKeyboardButton>> = ArrayList()
        rowList.add(list)
        return rowList
    }

}