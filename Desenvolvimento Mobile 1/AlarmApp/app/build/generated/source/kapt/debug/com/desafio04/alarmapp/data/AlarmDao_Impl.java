package com.desafio04.alarmapp.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AlarmDao_Impl implements AlarmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Alarm> __insertionAdapterOfAlarm;

  private final EntityDeletionOrUpdateAdapter<Alarm> __deletionAdapterOfAlarm;

  private final EntityDeletionOrUpdateAdapter<Alarm> __updateAdapterOfAlarm;

  private final SharedSQLiteStatement __preparedStmtOfSetAlarmEnabled;

  public AlarmDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlarm = new EntityInsertionAdapter<Alarm>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `alarms` (`id`,`hour`,`minute`,`label`,`isEnabled`,`repeatDays`,`vibrate`,`ringtone`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Alarm entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getHour());
        statement.bindLong(3, entity.getMinute());
        if (entity.getLabel() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLabel());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getRepeatDays() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRepeatDays());
        }
        final int _tmp_1 = entity.getVibrate() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getRingtone() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRingtone());
        }
      }
    };
    this.__deletionAdapterOfAlarm = new EntityDeletionOrUpdateAdapter<Alarm>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `alarms` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Alarm entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAlarm = new EntityDeletionOrUpdateAdapter<Alarm>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `alarms` SET `id` = ?,`hour` = ?,`minute` = ?,`label` = ?,`isEnabled` = ?,`repeatDays` = ?,`vibrate` = ?,`ringtone` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Alarm entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getHour());
        statement.bindLong(3, entity.getMinute());
        if (entity.getLabel() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLabel());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getRepeatDays() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRepeatDays());
        }
        final int _tmp_1 = entity.getVibrate() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getRingtone() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRingtone());
        }
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfSetAlarmEnabled = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE alarms SET isEnabled = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAlarm(final Alarm alarm, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAlarm.insertAndReturnId(alarm);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAlarm(final Alarm alarm, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAlarm.handle(alarm);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAlarm(final Alarm alarm, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAlarm.handle(alarm);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setAlarmEnabled(final int id, final boolean enabled,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetAlarmEnabled.acquire();
        int _argIndex = 1;
        final int _tmp = enabled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetAlarmEnabled.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Alarm>> getAllAlarms() {
    final String _sql = "SELECT * FROM alarms ORDER BY hour ASC, minute ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"alarms"}, false, new Callable<List<Alarm>>() {
      @Override
      @Nullable
      public List<Alarm> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
          final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfVibrate = CursorUtil.getColumnIndexOrThrow(_cursor, "vibrate");
          final int _cursorIndexOfRingtone = CursorUtil.getColumnIndexOrThrow(_cursor, "ringtone");
          final List<Alarm> _result = new ArrayList<Alarm>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Alarm _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHour;
            _tmpHour = _cursor.getInt(_cursorIndexOfHour);
            final int _tmpMinute;
            _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final boolean _tmpVibrate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfVibrate);
            _tmpVibrate = _tmp_1 != 0;
            final String _tmpRingtone;
            if (_cursor.isNull(_cursorIndexOfRingtone)) {
              _tmpRingtone = null;
            } else {
              _tmpRingtone = _cursor.getString(_cursorIndexOfRingtone);
            }
            _item = new Alarm(_tmpId,_tmpHour,_tmpMinute,_tmpLabel,_tmpIsEnabled,_tmpRepeatDays,_tmpVibrate,_tmpRingtone);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllAlarmsSync(final Continuation<? super List<Alarm>> $completion) {
    final String _sql = "SELECT * FROM alarms ORDER BY hour ASC, minute ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Alarm>>() {
      @Override
      @NonNull
      public List<Alarm> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
          final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfVibrate = CursorUtil.getColumnIndexOrThrow(_cursor, "vibrate");
          final int _cursorIndexOfRingtone = CursorUtil.getColumnIndexOrThrow(_cursor, "ringtone");
          final List<Alarm> _result = new ArrayList<Alarm>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Alarm _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHour;
            _tmpHour = _cursor.getInt(_cursorIndexOfHour);
            final int _tmpMinute;
            _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final boolean _tmpVibrate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfVibrate);
            _tmpVibrate = _tmp_1 != 0;
            final String _tmpRingtone;
            if (_cursor.isNull(_cursorIndexOfRingtone)) {
              _tmpRingtone = null;
            } else {
              _tmpRingtone = _cursor.getString(_cursorIndexOfRingtone);
            }
            _item = new Alarm(_tmpId,_tmpHour,_tmpMinute,_tmpLabel,_tmpIsEnabled,_tmpRepeatDays,_tmpVibrate,_tmpRingtone);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAlarmById(final int id, final Continuation<? super Alarm> $completion) {
    final String _sql = "SELECT * FROM alarms WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Alarm>() {
      @Override
      @Nullable
      public Alarm call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
          final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfVibrate = CursorUtil.getColumnIndexOrThrow(_cursor, "vibrate");
          final int _cursorIndexOfRingtone = CursorUtil.getColumnIndexOrThrow(_cursor, "ringtone");
          final Alarm _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHour;
            _tmpHour = _cursor.getInt(_cursorIndexOfHour);
            final int _tmpMinute;
            _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final boolean _tmpVibrate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfVibrate);
            _tmpVibrate = _tmp_1 != 0;
            final String _tmpRingtone;
            if (_cursor.isNull(_cursorIndexOfRingtone)) {
              _tmpRingtone = null;
            } else {
              _tmpRingtone = _cursor.getString(_cursorIndexOfRingtone);
            }
            _result = new Alarm(_tmpId,_tmpHour,_tmpMinute,_tmpLabel,_tmpIsEnabled,_tmpRepeatDays,_tmpVibrate,_tmpRingtone);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
