package com.aisecretary.app.voice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.*

class SpeechRecognizerManager(
    context: Context,
    private val onResult: (String) -> Unit
) : RecognitionListener {
    private val recognizer = SpeechRecognizer.createSpeechRecognizer(context)
    private val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
    }

    init { recognizer.setRecognitionListener(this) }

    fun startContinuous() { recognizer.startListening(intent) }
    fun destroy() { recognizer.destroy() }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        matches?.firstOrNull()?.let { onResult(it) }
        startContinuous() // restart for hands-free
    }
    override fun onPartialResults(p0: Bundle?) {}
    override fun onError(error: Int) { startContinuous() }
    override fun onReadyForSpeech(p0: Bundle?) {}
    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(p0: Float) {}
    override fun onBufferReceived(p0: ByteArray?) {}
    override fun onEndOfSpeech() {}
    override fun onEvent(p0: Int, p1: Bundle?) {}
}
