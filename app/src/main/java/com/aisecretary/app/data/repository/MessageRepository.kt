package com.aisecretary.app.data.repository

import android.content.Context
import com.aisecretary.app.data.db.AppDatabase
import com.aisecretary.app.data.db.MessageEntity

class MessageRepository(context: Context) {
    private val dao = AppDatabase.get(context).messageDao()
    suspend fun save(msg: MessageEntity) = dao.insert(msg)
    suspend fun getNew() = dao.getNew()
    suspend fun getByCategory(cat: String) = dao.getByCategory(cat)
}
