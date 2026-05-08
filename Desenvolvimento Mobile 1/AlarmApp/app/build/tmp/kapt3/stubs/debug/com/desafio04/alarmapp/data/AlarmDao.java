package com.desafio04.alarmapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0017"}, d2 = {"Lcom/desafio04/alarmapp/data/AlarmDao;", "", "deleteAlarm", "", "alarm", "Lcom/desafio04/alarmapp/data/Alarm;", "(Lcom/desafio04/alarmapp/data/Alarm;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlarmById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAlarms", "Landroidx/lifecycle/LiveData;", "", "getAllAlarmsSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAlarm", "", "setAlarmEnabled", "enabled", "", "(IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAlarm", "app_debug"})
@androidx.room.Dao()
public abstract interface AlarmDao {
    
    @androidx.room.Query(value = "SELECT * FROM alarms ORDER BY hour ASC, minute ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.desafio04.alarmapp.data.Alarm>> getAllAlarms();
    
    @androidx.room.Query(value = "SELECT * FROM alarms ORDER BY hour ASC, minute ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAlarmsSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.desafio04.alarmapp.data.Alarm>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM alarms WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAlarmById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.desafio04.alarmapp.data.Alarm> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE alarms SET isEnabled = :enabled WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setAlarmEnabled(int id, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}