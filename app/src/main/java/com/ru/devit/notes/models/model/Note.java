package com.ru.devit.notes.models.model;

public class Note {
    private final int noteId;
    private final String title;
    private final String description;

    public Note(int noteId, String title, String description) {
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
