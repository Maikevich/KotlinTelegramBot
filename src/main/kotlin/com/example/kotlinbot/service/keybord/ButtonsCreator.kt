package com.example.kotlinbot.service.keybord

 import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Service
class ButtonsCreator {

    fun getNewButton(text: String?, callBack: String?): InlineKeyboardButton? {
        val inlineKeyboardButton = InlineKeyboardButton()
        inlineKeyboardButton.text = text!!
        inlineKeyboardButton.callbackData = callBack
        return inlineKeyboardButton
    }

}