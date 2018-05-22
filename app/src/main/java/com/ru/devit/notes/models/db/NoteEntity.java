package com.ru.devit.notes.models.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true) private final int noteId;
    private final String title;
    private final String description;

    public NoteEntity(int noteId, String title, String description) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
