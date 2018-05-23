package com.ru.devit.notes.presentation;

import com.ru.devit.notes.models.db.NoteEntity;
import com.ru.devit.notes.models.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface NoteRepository {

    Flowable<List<Note>> getAllNotes();

    Flowable<Note> getNoteById(final int noteId);

    void clearDatabase();

    Single<Long> insertNote(Note note);
}
