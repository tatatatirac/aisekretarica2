package com.aisecretary.app.nlp

object Summarizer {
    fun summarize(text: String, maxSentences: Int = 2): String {
        val sentences = text.split(Regex("[.!?]")).filter { it.isNotBlank() }
        return sentences.take(maxSentences).joinToString(". ") + if (sentences.isNotEmpty()) "." else ""
    }
}
