
package com.aisecretary.app.voice

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class VoiceForegroundService : Service() {
    private lateinit var speech: SpeechRecognizerManager
    private lateinit var tts: TtsManager
    private lateinit var processor: VoiceCommandProcessor

    override fun onCreate() {
        super.onCreate()
        startForeground(1, buildNotification())
        tts = TtsManager(this)
        processor = VoiceCommandProcessor(this, tts)
        speech = SpeechRecognizerManager(this) { text ->
            processor.handle(text)
        }
        speech.startContinuous()
        tts.speak("Sekretarica je spremna")
    }

    private fun buildNotification(): Notification {
        val channelId = "voice_secretary"
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(NotificationChannel(channelId, "AI Sekretarica", NotificationManager.IMPORTANCE_LOW))
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("AI Sekretarica sluša")
            .setContentText("Hands-free mod aktivan")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
    override fun onDestroy() { speech.destroy(); tts.shutdown(); super.onDestroy() }
}
