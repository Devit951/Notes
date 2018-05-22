package com.ru.devit.notes.data.datasource.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ru.devit.notes.models.db.NoteEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note_table")
    Flowable<List<NoteEntity>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE noteId = :noteId")
    Flowable<NoteEntity> getNoteById(int noteId);

    @Insert
    void insertNote(NoteEntity noteEntity);
}
