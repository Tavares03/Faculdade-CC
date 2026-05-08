package com.desafio04.alarmapp.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.desafio04.alarmapp.receiver.AlarmReceiver
import java.util.Calendar

object AlarmScheduler {

    fun scheduleAlarm(context: Context, alarm: Alarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (alarm.repeatDays.isEmpty()) {
            // One-time alarm
            val calendar = getNextAlarmCalendar(alarm.hour, alarm.minute)
            setPreciseAlarm(context, alarmManager, alarm, calendar, alarm.id)
        } else {
            // Recurring alarm - schedule for each repeat day
            val days = alarm.repeatDays.split(",").mapNotNull { it.trim().toIntOrNull() }
            days.forEachIndexed { index, dayOfWeek ->
                val calendar = getCalendarForDayOfWeek(alarm.hour, alarm.minute, dayOfWeek)
                val requestCode = alarm.id * 10 + index
                setPreciseAlarm(context, alarmManager, alarm, calendar, requestCode)
            }
        }
    }

    fun cancelAlarm(context: Context, alarm: Alarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (alarm.repeatDays.isEmpty()) {
            cancelPendingIntent(context, alarmManager, alarm, alarm.id)
        } else {
            val days = alarm.repeatDays.split(",").mapNotNull { it.trim().toIntOrNull() }
            days.forEachIndexed { index, _ ->
                val requestCode = alarm.id * 10 + index
                cancelPendingIntent(context, alarmManager, alarm, requestCode)
            }
        }
    }

    private fun setPreciseAlarm(
        context: Context,
        alarmManager: AlarmManager,
        alarm: Alarm,
        calendar: Calendar,
        requestCode: Int
    ) {
        val intent = createAlarmIntent(context, alarm)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    private fun cancelPendingIntent(
        context: Context,
        alarmManager: AlarmManager,
        alarm: Alarm,
        requestCode: Int
    ) {
        val intent = createAlarmIntent(context, alarm)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun createAlarmIntent(context: Context, alarm: Alarm): Intent {
        return Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.EXTRA_ALARM_ID, alarm.id)
            putExtra(AlarmReceiver.EXTRA_ALARM_LABEL, alarm.label)
            putExtra(AlarmReceiver.EXTRA_ALARM_VIBRATE, alarm.vibrate)
            putExtra(AlarmReceiver.EXTRA_REPEAT_DAYS, alarm.repeatDays)
        }
    }

    private fun getNextAlarmCalendar(hour: Int, minute: Int): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }
    }

    private fun getCalendarForDayOfWeek(hour: Int, minute: Int, dayOfWeek: Int): Calendar {
        // dayOfWeek: 1=Mon, 2=Tue, ..., 7=Sun
        val androidDayOfWeek = when (dayOfWeek) {
            1 -> Calendar.MONDAY
            2 -> Calendar.TUESDAY
            3 -> Calendar.WEDNESDAY
            4 -> Calendar.THURSDAY
            5 -> Calendar.FRIDAY
            6 -> Calendar.SATURDAY
            7 -> Calendar.SUNDAY
            else -> Calendar.MONDAY
        }

        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            val today = get(Calendar.DAY_OF_WEEK)
            var daysUntil = androidDayOfWeek - today
            if (daysUntil < 0) daysUntil += 7
            if (daysUntil == 0 && timeInMillis <= System.currentTimeMillis()) daysUntil = 7
            add(Calendar.DAY_OF_MONTH, daysUntil)
        }
    }

    fun getTimeUntilAlarm(hour: Int, minute: Int): String {
        val now = Calendar.getInstance()
        val alarm = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= now.timeInMillis) add(Calendar.DAY_OF_MONTH, 1)
        }
        val diffMs = alarm.timeInMillis - now.timeInMillis
        val diffHours = (diffMs / (1000 * 60 * 60)).toInt()
        val diffMinutes = ((diffMs % (1000 * 60 * 60)) / (1000 * 60)).toInt()
        return when {
            diffHours == 0 -> "em ${diffMinutes}min"
            diffMinutes == 0 -> "em ${diffHours}h"
            else -> "em ${diffHours}h ${diffMinutes}min"
        }
    }
}
