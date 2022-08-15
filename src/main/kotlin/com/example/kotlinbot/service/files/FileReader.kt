package com.example.kotlinbot.service.files

import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.jvm.Throws


@Service
class FileReader {

    @Throws(NullPointerException::class)
    fun readFile(path: String): String? = this::class.java.classLoader.getResource(path).readText()

}