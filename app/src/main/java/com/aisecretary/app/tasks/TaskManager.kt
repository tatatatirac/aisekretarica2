package com.aisecretary.app.tasks

import android.content.Context

object TaskManager {
    private val tasks = mutableListOf<String>()
    fun add(task: String) { tasks.add(task) }
    fun list(): List<String> = tasks
}
