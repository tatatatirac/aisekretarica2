package com.aisecretary.app.data.db

import androidx.room.*

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(msg: MessageEntity)

    @Query("SELECT * FROM messages ORDER BY timestamp DESC")
    suspend fun getAll(): List<MessageEntity>

    @Query("SELECT * FROM messages WHERE isRead = 0 ORDER BY timestamp DESC")
    suspend fun getNew(): List<MessageEntity>

    @Query("SELECT * FROM messages WHERE category = :cat ORDER BY timestamp DESC")
    suspend fun getByCategory(cat: String): List<MessageEntity>
}
