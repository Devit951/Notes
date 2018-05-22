package com.ru.devit.notes.presentation;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.ru.devit.notes.data.datasource.db.AppDatabase;

public class NoteApp extends Application {

    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        if (database == null){
            database = Room.databaseBuilder(this , AppDatabase.class , "note_db").build();
        }
        return database;
    }
}
