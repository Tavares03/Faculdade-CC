package com.desafio04.alarmapp.data

import androidx.lifecycle.LiveData

class AlarmRepository(private val alarmDao: AlarmDao) {
    val allAlarms: LiveData<List<Alarm>> = alarmDao.getAllAlarms()

    suspend fun getAllAlarmsSync(): List<Alarm> = alarmDao.getAllAlarmsSync()

    suspend fun getAlarmById(id: Int): Alarm? = alarmDao.getAlarmById(id)

    suspend fun insert(alarm: Alarm): Long = alarmDao.insertAlarm(alarm)

    suspend fun update(alarm: Alarm) = alarmDao.updateAlarm(alarm)

    suspend fun delete(alarm: Alarm) = alarmDao.deleteAlarm(alarm)

    suspend fun setEnabled(id: Int, enabled: Boolean) = alarmDao.setAlarmEnabled(id, enabled)
}
