package com.ru.devit.notes.models.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "note_table")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true) private final int noteId;
    private final String title;
    private final String description;
    private final int noteColor;
    private String imgPath;

    public NoteEntity(int noteId, String title, String description , int noteColor) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
        this.noteColor = noteColor;
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

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getNoteColor(){
        return noteColor;
    }
}
