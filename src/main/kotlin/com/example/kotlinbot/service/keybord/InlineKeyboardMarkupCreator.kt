package com.example.kotlinbot.service.keybord

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Service
class InlineKeyboardMarkupCreator {

    fun getInlineKeyboardMarkup(list: List<InlineKeyboardButton>): List<List<InlineKeyboardButton>>? = listOf(list)

}