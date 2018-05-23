package com.ru.devit.notes.models.model;

import java.util.List;
import java.util.Locale;

public class Note {
    private int noteId;
    private final String title;
    private final String description;
    private final int noteColor;

    public Note(int noteId, String title, String description , int noteColor) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
        this.noteColor = noteColor;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault() , "Note %d , %s , %s \n" , noteId , title , description);
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNoteColor() {
        return noteColor;
    }

}
