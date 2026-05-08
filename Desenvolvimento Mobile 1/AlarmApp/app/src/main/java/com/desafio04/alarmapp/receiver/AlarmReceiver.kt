package com.desafio04.alarmapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.desafio04.alarmapp.service.AlarmService
import com.desafio04.alarmapp.ui.alarms.AlarmRingActivity

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val EXTRA_ALARM_ID = "extra_alarm_id"
        const val EXTRA_ALARM_LABEL = "extra_alarm_label"
        const val EXTRA_ALARM_VIBRATE = "extra_alarm_vibrate"
        const val EXTRA_REPEAT_DAYS = "extra_repeat_days"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Re-schedule all enabled alarms after reboot
            rescheduleAlarmsAfterBoot(context)
            return
        }

        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, -1)
        val alarmLabel = intent.getStringExtra(EXTRA_ALARM_LABEL) ?: "Alarme"
        val vibrate = intent.getBooleanExtra(EXTRA_ALARM_VIBRATE, true)

        // Start foreground service for alarm sound/vibration
        val serviceIntent = Intent(context, AlarmService::class.java).apply {
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(EXTRA_ALARM_LABEL, alarmLabel)
            putExtra(EXTRA_ALARM_VIBRATE, vibrate)
        }
        context.startForegroundService(serviceIntent)

        // Launch the ring screen
        val ringIntent = Intent(context, AlarmRingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(EXTRA_ALARM_LABEL, alarmLabel)
        }
        context.startActivity(ringIntent)
    }

    private fun rescheduleAlarmsAfterBoot(context: Context) {
        // Launch a coroutine via a worker or simply re-read from DB
        // For simplicity, we use a background thread here
        Thread {
            val db = com.desafio04.alarmapp.data.AlarmDatabase.getDatabase(context)
            val alarms = kotlinx.coroutines.runBlocking {
                db.alarmDao().getAllAlarmsSync()
            }
            alarms.filter { it.isEnabled }.forEach { alarm ->
                com.desafio04.alarmapp.data.AlarmScheduler.scheduleAlarm(context, alarm)
            }
        }.start()
    }
}
