package com.ru.devit.notes.presentation.notes;

import android.util.Log;

import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BasePresenter;
import com.ru.devit.notes.presentation.base.BaseView;

import java.util.List;

public class NotesPresenter extends BasePresenter<NotesPresenter.View> {

    private final NoteRepository repository;

    NotesPresenter(NoteRepository repository) {
        this.repository = repository;
    }

    void subscribeToData(){
        getView().showLoading();
        addDisposable(repository.getAllNotes()
                .subscribe(notes -> {
                    if (notes.isEmpty()){
                        getView().showEmptyScreen();
                    }
                    getView().showAllNotes(notes);
                } , throwable -> getView().hideLoading(), () -> getView().hideLoading()));
    }

    void onNoteClicked(int noteId) {
        getView().showDetailedNote(noteId);
    }

    void onAddBtnClicked(String noteTitle, String noteDesc , String imgPath , int noteColor) {
        if (!validateNote(noteTitle, noteDesc , imgPath)) {
            return;
        }
        final Note note = new Note(0 , noteTitle , noteDesc , noteColor);
        addDisposable(repository.insertNote(note)
                .subscribe(id -> {
                    note.setNoteId(id.intValue());
                    getView().addNote(note);
                    getView().showSuccessfullyNoteAdded();
                }));
    }

    void onActionClearDatabase() {
        repository.clearDatabase();
        getView().clearNotes();
    }

    private boolean validateNote(String noteTitle, String noteDesc , String imgPath) {
        if (noteTitle.isEmpty() || noteDesc.isEmpty() && imgPath != null){
            getView().showMessageTitleAndDescNotBeEmpty();
            return false;
        }
        return true;
    }

    public interface View extends BaseView{
        void showAllNotes(List<Note> notes);
        void showDetailedNote(final int noteId);
        void addNote(Note note);
        void clearNotes();
        void showMessageTitleAndDescNotBeEmpty();
        void showSuccessfullyNoteAdded();
        void showEmptyScreen();
        void showLoading();
        void hideLoading();
    }

}
