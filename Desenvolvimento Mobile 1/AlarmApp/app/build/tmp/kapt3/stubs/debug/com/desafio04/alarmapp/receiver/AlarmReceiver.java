package com.desafio04.alarmapp.receiver;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u00a8\u0006\u000b"}, d2 = {"Lcom/desafio04/alarmapp/receiver/AlarmReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "rescheduleAlarmsAfterBoot", "Companion", "app_debug"})
public final class AlarmReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ALARM_ID = "extra_alarm_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ALARM_LABEL = "extra_alarm_label";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ALARM_VIBRATE = "extra_alarm_vibrate";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_REPEAT_DAYS = "extra_repeat_days";
    @org.jetbrains.annotations.NotNull()
    public static final com.desafio04.alarmapp.receiver.AlarmReceiver.Companion Companion = null;
    
    public AlarmReceiver() {
        super();
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void rescheduleAlarmsAfterBoot(android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/desafio04/alarmapp/receiver/AlarmReceiver$Companion;", "", "()V", "EXTRA_ALARM_ID", "", "EXTRA_ALARM_LABEL", "EXTRA_ALARM_VIBRATE", "EXTRA_REPEAT_DAYS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}