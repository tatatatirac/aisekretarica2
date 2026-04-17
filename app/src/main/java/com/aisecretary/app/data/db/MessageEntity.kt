package com.aisecretary.app.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sender: String,
    val originalText: String,
    val summary: String,
    val category: String, // urgent, callback, info, sales
    val timestamp: Long,
    val audioPath: String?,
    val isRead: Boolean = false
)
