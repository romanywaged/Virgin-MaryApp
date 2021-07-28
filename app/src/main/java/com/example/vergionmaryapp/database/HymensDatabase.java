package com.example.vergionmaryapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vergionmaryapp.database.entities.Hymens;

@Database(entities = {Hymens.class}, version = 3, exportSchema = false)

public abstract class HymensDatabase extends RoomDatabase {

    public static HymensDatabase instance;

    public abstract HymensDao getDao();

    public static synchronized HymensDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HymensDatabase.class, "HymensDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
