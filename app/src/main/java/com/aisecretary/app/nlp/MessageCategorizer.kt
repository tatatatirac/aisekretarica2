package com.aisecretary.app.nlp

object MessageCategorizer {
    fun categorize(text: String): String {
        val t = text.lowercase()
        return when {
            listOf("urgent","asap","emergency","immediately").any { it in t } -> "urgent"
            listOf("call back","callback","return call").any { it in t } -> "callback"
            listOf("price","quote","buy","purchase","sales").any { it in t } -> "sales"
            else -> "info"
        }
    }
}
