package com.desafio04.alarmapp.ui.alarms

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.desafio04.alarmapp.databinding.ActivityAlarmRingBinding
import com.desafio04.alarmapp.receiver.AlarmReceiver
import com.desafio04.alarmapp.service.AlarmService

class AlarmRingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmRingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Show over lock screen
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        binding = ActivityAlarmRingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val label = intent.getStringExtra(AlarmReceiver.EXTRA_ALARM_LABEL) ?: "Alarme"
        binding.tvAlarmLabel.text = label
        binding.tvAlarmTime.text = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
            .format(java.util.Date())

        binding.btnDismiss.setOnClickListener { stopAlarmAndFinish() }
        binding.btnSnooze.setOnClickListener { snoozeAlarm() }
    }

    private fun stopAlarmAndFinish() {
        val stopIntent = Intent(this, AlarmService::class.java).apply {
            action = AlarmService.ACTION_STOP_ALARM
        }
        startService(stopIntent)
        finish()
    }

    private fun snoozeAlarm() {
        // Snooze for 5 minutes
        val stopIntent = Intent(this, AlarmService::class.java).apply {
            action = AlarmService.ACTION_STOP_ALARM
        }
        startService(stopIntent)

        val alarmManager = getSystemService(ALARM_SERVICE) as android.app.AlarmManager
        val snoozeIntent = Intent(this, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.EXTRA_ALARM_LABEL, binding.tvAlarmLabel.text.toString())
            putExtra(AlarmReceiver.EXTRA_ALARM_VIBRATE, true)
        }
        val pendingIntent = android.app.PendingIntent.getBroadcast(
            this, 9999, snoozeIntent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )
        val snoozeTime = System.currentTimeMillis() + 5 * 60 * 1000
        alarmManager.setExactAndAllowWhileIdle(
            android.app.AlarmManager.RTC_WAKEUP, snoozeTime, pendingIntent
        )
        finish()
    }

    override fun onBackPressed() {
        // Do nothing — user must explicitly dismiss
    }
}
