package com.selena.efinalrosalestello.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.selena.efinalrosalestello.model.ShowEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ShowEntity.class}, version = 1)
public abstract class CatDatabase extends RoomDatabase {

    public abstract CatDao catDao();
    private static volatile CatDatabase db;

    public static final ExecutorService dataBaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static CatDatabase getInstance(Context context){
        if(db == null){
            synchronized (CatDatabase.class){
                if (db == null){
                    db = Room.databaseBuilder(context.getApplicationContext(),
                            CatDatabase.class, "shows-database").build();
                }
            }
        }
        return db;
    }
}
