package com.aisecretary.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            val passphrase = "change_me".toCharArray() // Use Keystore in production
            val factory = SupportFactory(passphrase)
            Room.databaseBuilder(context, AppDatabase::class.java, "secretary.db")
                .openHelperFactory(factory)
                .build().also { INSTANCE = it }
        }
    }
}
