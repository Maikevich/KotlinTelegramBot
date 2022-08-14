package com.example.kotlinbot.util

import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return ofClass.enclosingClass?.takeIf {
        ofClass.enclosingClass.kotlin.companionObject?.java == ofClass
    } ?: ofClass
}

fun <R : Any> R.logger(): Lazy<org.slf4j.Logger> {
    return lazy { LoggerFactory.getLogger(unwrapCompanionClass(this.javaClass).name) }
}