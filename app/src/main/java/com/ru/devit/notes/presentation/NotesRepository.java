package com.ru.devit.notes.presentation;

import com.ru.devit.notes.models.db.NoteEntity;

import io.reactivex.Observable;

public interface NotesRepository {

    Observable<NoteEntity> getAllNotes();

    Observable<NoteEntity> getNoteById(final int noteId);
}
