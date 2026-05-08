package com.desafio04.alarmapp.ui.alarms

import android.app.Application
import androidx.lifecycle.*
import com.desafio04.alarmapp.data.*
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AlarmRepository
    val allAlarms: LiveData<List<Alarm>>

    init {
        val dao = AlarmDatabase.getDatabase(application).alarmDao()
        repository = AlarmRepository(dao)
        allAlarms = repository.allAlarms
    }

    fun insertAlarm(alarm: Alarm, onInserted: ((Long) -> Unit)? = null) = viewModelScope.launch {
        val id = repository.insert(alarm)
        onInserted?.invoke(id)
        if (alarm.isEnabled) {
            AlarmScheduler.scheduleAlarm(getApplication(), alarm.copy(id = id.toInt()))
        }
    }

    fun updateAlarm(alarm: Alarm) = viewModelScope.launch {
        repository.update(alarm)
        AlarmScheduler.cancelAlarm(getApplication(), alarm)
        if (alarm.isEnabled) {
            AlarmScheduler.scheduleAlarm(getApplication(), alarm)
        }
    }

    fun deleteAlarm(alarm: Alarm) = viewModelScope.launch {
        AlarmScheduler.cancelAlarm(getApplication(), alarm)
        repository.delete(alarm)
    }

    fun toggleAlarm(alarm: Alarm, enabled: Boolean) = viewModelScope.launch {
        repository.setEnabled(alarm.id, enabled)
        if (enabled) {
            AlarmScheduler.scheduleAlarm(getApplication(), alarm)
        } else {
            AlarmScheduler.cancelAlarm(getApplication(), alarm)
        }
    }

    suspend fun getAlarmById(id: Int): Alarm? = repository.getAlarmById(id)
}
