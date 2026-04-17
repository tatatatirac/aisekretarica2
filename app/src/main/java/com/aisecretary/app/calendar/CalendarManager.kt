package com.aisecretary.app.calendar

import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import java.util.*

class CalendarManager(private val context: Context) {
    fun createEventFromText(text: String) {
        // Simple parser: "tomorrow 3pm with client"
        val cal = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1); set(Calendar.HOUR_OF_DAY, 15) }
        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, cal.timeInMillis)
            put(CalendarContract.Events.DTEND, cal.timeInMillis + 3600000)
            put(CalendarContract.Events.TITLE, "Meeting: $text")
            put(CalendarContract.Events.CALENDAR_ID, 1)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
    }
}
