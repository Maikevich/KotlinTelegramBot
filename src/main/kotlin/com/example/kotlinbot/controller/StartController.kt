package com.example.kotlinbot.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StartController {

    @GetMapping(value = ["/"])
    @ResponseBody
    fun hello(): String = "Get back to work"


}