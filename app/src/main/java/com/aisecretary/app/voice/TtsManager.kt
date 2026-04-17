package com.aisecretary.app.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TtsManager(context: Context) : TextToSpeech.OnInitListener {
    private val tts = TextToSpeech(context, this)
    private var ready = false

    override fun onInit(status: Int) { ready = status == TextToSpeech.SUCCESS }

    fun speak(text: String) {
        if (!ready) return
        tts.language = Locale.getDefault()
        tts.speak(text, TextToSpeech.QUEUE_ADD, null, UUID.randomUUID().toString())
    }
    fun shutdown() { tts.shutdown() }
}
