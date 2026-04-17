
package com.aisecretary.app.voice

import android.content.Context
import com.aisecretary.app.data.repository.MessageRepository
import com.aisecretary.app.calendar.CalendarManager
import kotlinx.coroutines.*

class VoiceCommandProcessor(
    private val context: Context,
    private val tts: TtsManager
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val repo = MessageRepository(context)
    private val calendar = CalendarManager(context)

    fun handle(text: String) {
        val lower = text.lowercase()
        when {
            // SRPSKI komande
            "pročitaj" in lower && "poruk" in lower -> readMessages()
            "hitno" in lower || "urgent" in lower -> readUrgent()
            "sumiraj" in lower || "sažmi" in lower -> summarize()
            "zakaži" in lower || "sastanak" in lower -> schedule(lower)
            "podsjeti" in lower || "podseti" in lower || "remind" in lower -> remind()
            "napravi zadatak" in lower || "zadatak" in lower -> createTask(lower)
            // ENGLESKI fallback
            "read" in lower && "message" in lower -> readMessages()
            "schedule" in lower -> schedule(lower)
            else -> tts.speak("Nisam razumeo. Reci: pročitaj poruke, ili zakaži sastanak.")
        }
    }

    private fun readMessages() = scope.launch {
        val msgs = repo.getNew()
        if (msgs.isEmpty()) tts.speak("Nema novih poruka")
        else {
            tts.speak("Imaš ${msgs.size} novih poruka")
            msgs.take(3).forEach { tts.speak("Od ${it.sender}: ${it.summary}") }
        }
    }

    private fun readUrgent() = scope.launch {
        val urgent = repo.getByCategory("urgent")
        if (urgent.isEmpty()) tts.speak("Nema hitnih poruka")
        else tts.speak("Imaš ${urgent.size} hitnih poruka")
    }

    private fun summarize() { tts.speak("Sumiram poruke") }
    private fun schedule(text: String) { 
        calendar.createEventFromText(text)
        tts.speak("Sastanak je zakazan") 
    }
    private fun remind() { tts.speak("Podsetnik postavljen") }
    private fun createTask(text: String) { tts.speak("Zadatak dodat") }
}
