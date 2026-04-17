
package com.aisecretary.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aisecretary.app.voice.TtsManager
import com.aisecretary.app.utils.PermissionsHelper

class MainActivity : ComponentActivity() {
    private lateinit var tts: TtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TtsManager(this)
        PermissionsHelper.requestAll(this)

        setContent {
            MaterialTheme(colorScheme = dynamicDarkColorScheme(this)) {
                var listening by remember { mutableStateOf(false) }
                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(onClick = {
                            listening = !listening
                            if (listening) startVoiceService() else stopVoiceService()
                        }) { Text(if (listening) "Zaustavi" else "Pokreni Sekretaricu") }
                    }
                ) { padding ->
                    Column(Modifier.padding(padding).padding(24.dp)) {
                        Text("AI Sekretarica", style = MaterialTheme.typography.headlineLarge)
                        Spacer(Modifier.height(16.dp))
                        Text("Glasovni asistent za male firme. Radi bez ruku.")
                        Spacer(Modifier.height(8.dp))
                        Text("Komande: 'pročitaj poruke', 'hitno', 'zakaži sastanak', 'podseti me'")
                        Spacer(Modifier.height(24.dp))
                        Button(onClick = { tts.speak("Sekretarica je spremna. Imaš 3 nove poruke.") }) {
                            Text("Testiraj glas")
                        }
                    }
                }
            }
        }
    }

    private fun startVoiceService() {
        startForegroundService(android.content.Intent(this, com.aisecretary.app.voice.VoiceForegroundService::class.java))
    }
    private fun stopVoiceService() {
        stopService(android.content.Intent(this, com.aisecretary.app.voice.VoiceForegroundService::class.java))
    }
}
