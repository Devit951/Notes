package com.ru.devit.notes.data;

import android.util.Log;

import com.ru.devit.notes.data.datasource.db.NoteDao;
import com.ru.devit.notes.models.db.NoteEntity;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.models.model.mapper.NoteEntityToNote;
import com.ru.devit.notes.presentation.NoteRepository;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class NoteLocalRepository implements NoteRepository {

    private final NoteDao noteDao;
    private final NoteEntityToNote mapper;

    public NoteLocalRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
        mapper = new NoteEntityToNote();
    }

    @Override
    public Flowable<List<Note>> getAllNotes() {
        return noteDao.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mapper::toNoteList)
                .toFlowable();
    }

    @Override
    public Flowable<Note> getNoteById(int noteId) {
        return noteDao.getNoteById(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mapper::toNote)
                .toFlowable();
    }

    @Override
    public void clearDatabase() {
        doBackgroundTask(noteDao::clearDatabase).subscribe();
    }

    @Override
    public Single<Long> insertNote(Note note) {
        return Single.fromCallable(() -> noteDao.insertNote(mapper.fromNote(note)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Completable doBackgroundTask(Action action){
        return Completable.fromAction(action)
                .subscribeOn(Schedulers.io());
    }
}
