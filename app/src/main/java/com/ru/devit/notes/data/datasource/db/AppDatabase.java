package com.ru.devit.notes.data.datasource.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ru.devit.notes.models.db.NoteEntity;

@Database(entities = {NoteEntity.class} , version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
