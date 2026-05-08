package com.desafio04.alarmapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ(\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rJ\u0016\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ0\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rH\u0002\u00a8\u0006\u001b"}, d2 = {"Lcom/desafio04/alarmapp/data/AlarmScheduler;", "", "()V", "cancelAlarm", "", "context", "Landroid/content/Context;", "alarm", "Lcom/desafio04/alarmapp/data/Alarm;", "cancelPendingIntent", "alarmManager", "Landroid/app/AlarmManager;", "requestCode", "", "createAlarmIntent", "Landroid/content/Intent;", "getCalendarForDayOfWeek", "Ljava/util/Calendar;", "hour", "minute", "dayOfWeek", "getNextAlarmCalendar", "getTimeUntilAlarm", "", "scheduleAlarm", "setPreciseAlarm", "calendar", "app_debug"})
public final class AlarmScheduler {
    @org.jetbrains.annotations.NotNull()
    public static final com.desafio04.alarmapp.data.AlarmScheduler INSTANCE = null;
    
    private AlarmScheduler() {
        super();
    }
    
    public final void scheduleAlarm(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm) {
    }
    
    public final void cancelAlarm(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm) {
    }
    
    private final void setPreciseAlarm(android.content.Context context, android.app.AlarmManager alarmManager, com.desafio04.alarmapp.data.Alarm alarm, java.util.Calendar calendar, int requestCode) {
    }
    
    private final void cancelPendingIntent(android.content.Context context, android.app.AlarmManager alarmManager, com.desafio04.alarmapp.data.Alarm alarm, int requestCode) {
    }
    
    private final android.content.Intent createAlarmIntent(android.content.Context context, com.desafio04.alarmapp.data.Alarm alarm) {
        return null;
    }
    
    private final java.util.Calendar getNextAlarmCalendar(int hour, int minute) {
        return null;
    }
    
    private final java.util.Calendar getCalendarForDayOfWeek(int hour, int minute, int dayOfWeek) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeUntilAlarm(int hour, int minute) {
        return null;
    }
}