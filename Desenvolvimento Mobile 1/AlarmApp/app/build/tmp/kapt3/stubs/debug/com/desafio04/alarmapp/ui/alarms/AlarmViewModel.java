package com.desafio04.alarmapp.ui.alarms;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0016J\u0016\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/desafio04/alarmapp/ui/alarms/AlarmViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "allAlarms", "Landroidx/lifecycle/LiveData;", "", "Lcom/desafio04/alarmapp/data/Alarm;", "getAllAlarms", "()Landroidx/lifecycle/LiveData;", "repository", "Lcom/desafio04/alarmapp/data/AlarmRepository;", "deleteAlarm", "Lkotlinx/coroutines/Job;", "alarm", "getAlarmById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAlarm", "onInserted", "Lkotlin/Function1;", "", "", "toggleAlarm", "enabled", "", "updateAlarm", "app_debug"})
public final class AlarmViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.desafio04.alarmapp.data.AlarmRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.desafio04.alarmapp.data.Alarm>> allAlarms = null;
    
    public AlarmViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.desafio04.alarmapp.data.Alarm>> getAllAlarms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onInserted) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job toggleAlarm(@org.jetbrains.annotations.NotNull()
    com.desafio04.alarmapp.data.Alarm alarm, boolean enabled) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAlarmById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.desafio04.alarmapp.data.Alarm> $completion) {
        return null;
    }
}