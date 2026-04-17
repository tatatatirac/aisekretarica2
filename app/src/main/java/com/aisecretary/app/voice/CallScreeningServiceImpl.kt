package com.aisecretary.app.voice

import android.telecom.Call
import android.telecom.CallScreeningService

class CallScreeningServiceImpl : CallScreeningService() {
    override fun onScreenCall(details: Call.Details) {
        // Android 10+ cannot auto-answer unless default dialer
        // We log and allow; suggest call forwarding in UI
        val response = CallResponse.Builder().setDisallowCall(false).setRejectCall(false).setSkipCallLog(false).build()
        respondToCall(details, response)
    }
}
