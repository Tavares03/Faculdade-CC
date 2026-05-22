package com.example.carro2.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.carro2.data.entity.Car;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CarDao_Impl implements CarDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Car> __insertionAdapterOfCar;

  private final EntityDeletionOrUpdateAdapter<Car> __deletionAdapterOfCar;

  public CarDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCar = new EntityInsertionAdapter<Car>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cars` (`id`,`brand`,`model`,`price`,`imageUrl`,`description`,`engine`,`power`,`torque`,`acceleration`,`topSpeed`,`transmission`,`origin`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Car entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBrand());
        statement.bindString(3, entity.getModel());
        statement.bindDouble(4, entity.getPrice());
        statement.bindString(5, entity.getImageUrl());
        statement.bindString(6, entity.getDescription());
        statement.bindString(7, entity.getEngine());
        statement.bindString(8, entity.getPower());
        statement.bindString(9, entity.getTorque());
        statement.bindString(10, entity.getAcceleration());
        statement.bindString(11, entity.getTopSpeed());
        statement.bindString(12, entity.getTransmission());
        statement.bindString(13, entity.getOrigin());
      }
    };
    this.__deletionAdapterOfCar = new EntityDeletionOrUpdateAdapter<Car>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cars` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Car entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertCar(final Car car, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCar.insert(car);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCar(final Car car, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCar.handle(car);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Car>> getAllCars() {
    final String _sql = "SELECT * FROM cars ORDER BY brand ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"cars"}, false, new Callable<List<Car>>() {
      @Override
      @Nullable
      public List<Car> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfEngine = CursorUtil.getColumnIndexOrThrow(_cursor, "engine");
          final int _cursorIndexOfPower = CursorUtil.getColumnIndexOrThrow(_cursor, "power");
          final int _cursorIndexOfTorque = CursorUtil.getColumnIndexOrThrow(_cursor, "torque");
          final int _cursorIndexOfAcceleration = CursorUtil.getColumnIndexOrThrow(_cursor, "acceleration");
          final int _cursorIndexOfTopSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "topSpeed");
          final int _cursorIndexOfTransmission = CursorUtil.getColumnIndexOrThrow(_cursor, "transmission");
          final int _cursorIndexOfOrigin = CursorUtil.getColumnIndexOrThrow(_cursor, "origin");
          final List<Car> _result = new ArrayList<Car>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Car _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpBrand;
            _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            final String _tmpModel;
            _tmpModel = _cursor.getString(_cursorIndexOfModel);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpEngine;
            _tmpEngine = _cursor.getString(_cursorIndexOfEngine);
            final String _tmpPower;
            _tmpPower = _cursor.getString(_cursorIndexOfPower);
            final String _tmpTorque;
            _tmpTorque = _cursor.getString(_cursorIndexOfTorque);
            final String _tmpAcceleration;
            _tmpAcceleration = _cursor.getString(_cursorIndexOfAcceleration);
            final String _tmpTopSpeed;
            _tmpTopSpeed = _cursor.getString(_cursorIndexOfTopSpeed);
            final String _tmpTransmission;
            _tmpTransmission = _cursor.getString(_cursorIndexOfTransmission);
            final String _tmpOrigin;
            _tmpOrigin = _cursor.getString(_cursorIndexOfOrigin);
            _item = new Car(_tmpId,_tmpBrand,_tmpModel,_tmpPrice,_tmpImageUrl,_tmpDescription,_tmpEngine,_tmpPower,_tmpTorque,_tmpAcceleration,_tmpTopSpeed,_tmpTransmission,_tmpOrigin);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
