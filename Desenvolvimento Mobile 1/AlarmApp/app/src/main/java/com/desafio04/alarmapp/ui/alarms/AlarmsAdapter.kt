package com.desafio04.alarmapp.ui.alarms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.desafio04.alarmapp.data.Alarm
import com.desafio04.alarmapp.data.AlarmScheduler
import com.desafio04.alarmapp.databinding.ItemAlarmBinding

class AlarmsAdapter(
    private val onToggle: (Alarm, Boolean) -> Unit,
    private val onEdit: (Alarm) -> Unit,
    private val onDelete: (Alarm) -> Unit
) : ListAdapter<Alarm, AlarmsAdapter.AlarmViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Alarm>() {
            override fun areItemsTheSame(a: Alarm, b: Alarm) = a.id == b.id
            override fun areContentsTheSame(a: Alarm, b: Alarm) = a == b
        }
    }

    inner class AlarmViewHolder(private val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(alarm: Alarm) {
            binding.tvTime.text = alarm.getFormattedTime()
            binding.tvLabel.text = alarm.label.ifEmpty { "Alarme" }
            binding.tvRepeat.text = alarm.getRepeatText()
            binding.tvTimeUntil.text = if (alarm.isEnabled)
                AlarmScheduler.getTimeUntilAlarm(alarm.hour, alarm.minute)
            else ""

            // Avoid triggering listener on bind
            binding.switchEnabled.setOnCheckedChangeListener(null)
            binding.switchEnabled.isChecked = alarm.isEnabled
            binding.switchEnabled.setOnCheckedChangeListener { _, checked ->
                onToggle(alarm, checked)
            }

            // Dim if disabled
            val alpha = if (alarm.isEnabled) 1f else 0.45f
            binding.tvTime.alpha = alpha
            binding.tvLabel.alpha = alpha
            binding.tvRepeat.alpha = alpha

            binding.root.setOnClickListener { onEdit(alarm) }
            binding.btnDelete.setOnClickListener { onDelete(alarm) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
