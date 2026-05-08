package com.desafio04.alarmapp.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.desafio04.alarmapp.data.Alarm
import com.desafio04.alarmapp.databinding.ActivityAddEditAlarmBinding
import com.desafio04.alarmapp.ui.alarms.AlarmViewModel
import com.desafio04.alarmapp.data.AlarmScheduler
import kotlinx.coroutines.launch

class AddEditAlarmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ALARM_ID = "extra_alarm_id"
    }

    private lateinit var binding: ActivityAddEditAlarmBinding
    private val viewModel: AlarmViewModel by viewModels()
    private var editingAlarm: Alarm? = null
    private val selectedDays = mutableSetOf<Int>() // 1=Mon ... 7=Sun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, -1)

        if (alarmId != -1) {
            loadAlarm(alarmId)
            binding.tvTitle.text = "Editar Alarme"
            binding.btnSave.text = "Atualizar"
        } else {
            binding.tvTitle.text = "Novo Alarme"
            binding.timePicker.setIs24HourView(true)
        }

        setupDayChips()
        setupSaveButton()
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun loadAlarm(id: Int) {
        lifecycleScope.launch {
            val alarm = viewModel.getAlarmById(id) ?: return@launch
            editingAlarm = alarm

            binding.timePicker.setIs24HourView(true)
            binding.timePicker.hour = alarm.hour
            binding.timePicker.minute = alarm.minute
            binding.etLabel.setText(alarm.label)
            binding.switchVibrate.isChecked = alarm.vibrate

            // Restore selected days
            if (alarm.repeatDays.isNotEmpty()) {
                alarm.repeatDays.split(",").mapNotNull { it.trim().toIntOrNull() }
                    .forEach { day ->
                        selectedDays.add(day)
                        updateDayChip(day, true)
                    }
            }
        }
    }

    private fun setupDayChips() {
        val chipMap = mapOf(
            1 to binding.chipMon,
            2 to binding.chipTue,
            3 to binding.chipWed,
            4 to binding.chipThu,
            5 to binding.chipFri,
            6 to binding.chipSat,
            7 to binding.chipSun
        )

        chipMap.forEach { (day, chip) ->
            chip.setOnClickListener {
                if (selectedDays.contains(day)) {
                    selectedDays.remove(day)
                    updateDayChip(day, false)
                } else {
                    selectedDays.add(day)
                    updateDayChip(day, true)
                }
            }
        }
    }

    private fun updateDayChip(day: Int, selected: Boolean) {
        val chip = when (day) {
            1 -> binding.chipMon
            2 -> binding.chipTue
            3 -> binding.chipWed
            4 -> binding.chipThu
            5 -> binding.chipFri
            6 -> binding.chipSat
            7 -> binding.chipSun
            else -> return
        }
        chip.isChecked = selected
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val label = binding.etLabel.text.toString().trim()
            if (label.isEmpty()) {
                binding.tilLabel.error = "Informe uma mensagem para o alarme"
                return@setOnClickListener
            }
            binding.tilLabel.error = null

            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val vibrate = binding.switchVibrate.isChecked
            val repeatDays = selectedDays.sorted().joinToString(",")

            val alarm = Alarm(
                id = editingAlarm?.id ?: 0,
                hour = hour,
                minute = minute,
                label = label,
                isEnabled = true,
                repeatDays = repeatDays,
                vibrate = vibrate
            )

            if (editingAlarm != null) {
                viewModel.updateAlarm(alarm)
                val timeUntil = AlarmScheduler.getTimeUntilAlarm(hour, minute)
                Toast.makeText(this, "Alarme atualizado — $timeUntil", Toast.LENGTH_LONG).show()
            } else {
                viewModel.insertAlarm(alarm)
                val timeUntil = AlarmScheduler.getTimeUntilAlarm(hour, minute)
                Toast.makeText(this, "Alarme definido — $timeUntil", Toast.LENGTH_LONG).show()
            }

            finish()
        }
    }
}
