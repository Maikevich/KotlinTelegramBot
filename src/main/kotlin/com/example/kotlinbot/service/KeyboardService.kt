package com.example.kotlinbot.service

import com.example.kotlinbot.enums.Commands
import com.example.kotlinbot.enums.JavaThemes
import com.example.kotlinbot.enums.TopicNames
import com.example.kotlinbot.service.keybord.ButtonsCreator
import com.example.kotlinbot.service.keybord.InlineKeyboardMarkupCreator
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton


@Service
class KeyboardService(
    private val buttonsCreator: ButtonsCreator,
    private val inlineKeyboardMarkupCreator: InlineKeyboardMarkupCreator
) {

    private val inlineKeyboardMarkup: InlineKeyboardMarkup = InlineKeyboardMarkup()

    fun getTopicsInlineKeyboard(b: Boolean): InlineKeyboardMarkup? {
        val buttonsList: MutableList<InlineKeyboardButton> = java.util.ArrayList()
        if (b) {
            for (tp in TopicNames.values()) {
                buttonsCreator.getNewButton(tp.name, tp.name)?.let { buttonsList.add(it) }
            }
        } else {
            for (tp in JavaThemes.values()) {
                buttonsCreator.getNewButton(tp.name, tp.name)?.let { buttonsList.add(it) }
            }
        }
        inlineKeyboardMarkup.keyboard = inlineKeyboardMarkupCreator
            .getInlineKeyboardMarkup(buttonsList) as MutableList<MutableList<InlineKeyboardButton>>
        return inlineKeyboardMarkup
    }

    fun restartInlineKeyboard(): InlineKeyboardMarkup? {
        val buttonsList: MutableList<InlineKeyboardButton> = ArrayList()
        buttonsCreator.getNewButton(Commands.RESTART.name, Commands.RESTART.name)?.let { buttonsList.add(it) }
        inlineKeyboardMarkup.keyboard = inlineKeyboardMarkupCreator
            .getInlineKeyboardMarkup(buttonsList) as MutableList<MutableList<InlineKeyboardButton>>
        return inlineKeyboardMarkup
    }
}