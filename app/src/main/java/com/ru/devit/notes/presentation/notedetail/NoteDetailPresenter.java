package com.ru.devit.notes.presentation.notedetail;

import android.util.Log;

import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BasePresenter;
import com.ru.devit.notes.presentation.base.BaseView;

public class NoteDetailPresenter extends BasePresenter<NoteDetailPresenter.View> {

    private final NoteRepository repository;
    private final int noteId;

    public NoteDetailPresenter(NoteRepository repository , int noteId) {
        this.repository = repository;
        this.noteId = noteId;
    }

    void subscribeToData() {
        addDisposable(repository.getNoteById(noteId)
                .subscribe(note -> getView().showNoteDetail(note) ,
                        throwable -> {

                        }));
    }

    public interface View extends BaseView{
        void showNoteDetail(Note note);
    }
}
