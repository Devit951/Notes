package com.ru.devit.notes.presentation;

import com.ru.devit.notes.models.model.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface NoteRepository {

    Flowable<List<Note>> getAllNotes();

    Flowable<Note> getNoteById(final int noteId);

    void clearDatabase();

    void insertNote(Note note);
}
