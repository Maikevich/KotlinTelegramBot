package com.example.kotlinbot.service

import com.example.kotlinbot.config.BotConfig
import com.example.kotlinbot.util.logger
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage.SendMessageBuilder
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

@Service
class Bot(
    private val botConfig: BotConfig,
    private val messageConstructor: MessageConstructor,
    private val updateRecognizer: UpdateReader,
    private val userService: UserService
) : TelegramLongPollingBot() {


    override fun onUpdateReceived(update: Update) {
        update.updateId
        var user = userService.getUserByChatId(update)
        sendMessage(messageConstructor.constructMessage(update,user), updateRecognizer.readUpdate(update,user)!!)
    }

    private fun sendMessage(str: String, builder: SendMessageBuilder) {
        try {
            builder.text(str!!)
            execute(builder.build())
        } catch (e: TelegramApiException) {
            LOG.debug("executing $builder to build message is failed")
        }
    }

    override fun getBotToken(): String = botConfig.getBotToken()
    override fun getBotUsername(): String = botConfig.getBotUserName()

    companion object {
        val LOG by logger()
    }

}

