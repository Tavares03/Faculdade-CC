package com.desafio04.alarmapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hour: Int,
    val minute: Int,
    val label: String,
    val isEnabled: Boolean = true,
    val repeatDays: String = "", // "1,2,3,4,5" = Mon-Fri, "" = once
    val vibrate: Boolean = true,
    val ringtone: String = "default"
) {
    fun getFormattedTime(): String {
        return String.format("%02d:%02d", hour, minute)
    }

    fun getRepeatText(): String {
        if (repeatDays.isEmpty()) return "Uma vez"
        val days = repeatDays.split(",").map { it.trim().toIntOrNull() ?: 0 }
        val dayNames = mapOf(
            1 to "Seg", 2 to "Ter", 3 to "Qua",
            4 to "Qui", 5 to "Sex", 6 to "Sáb", 7 to "Dom"
        )
        return when {
            days.containsAll(listOf(1, 2, 3, 4, 5)) && days.size == 5 -> "Dias úteis"
            days.containsAll(listOf(6, 7)) && days.size == 2 -> "Final de semana"
            days.size == 7 -> "Todos os dias"
            else -> days.mapNotNull { dayNames[it] }.joinToString(", ")
        }
    }
}
