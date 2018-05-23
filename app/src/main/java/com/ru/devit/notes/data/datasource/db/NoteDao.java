package com.ru.devit.notes.data.datasource.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ru.devit.notes.models.db.NoteEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note_table")
    Single<List<NoteEntity>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE noteId = :noteId")
    Single<NoteEntity> getNoteById(int noteId);

    @Insert
    long insertNote(NoteEntity noteEntity);

    @Query("DELETE FROM note_table")
    void clearDatabase();
}
