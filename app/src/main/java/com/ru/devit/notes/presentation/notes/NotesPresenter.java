package com.ru.devit.notes.presentation.notes;

import com.ru.devit.notes.data.NoteLocalRepository;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BasePresenter;
import com.ru.devit.notes.presentation.base.BaseView;

import java.util.List;

import io.reactivex.functions.Consumer;

public class NotesPresenter extends BasePresenter<NotesPresenter.View> {

    private final NoteRepository repository;

    NotesPresenter(NoteRepository repository) {
        this.repository = repository;
    }

    public void subscribeToData(){
        repository.getAllNotes()
                .subscribe(notes -> getView().showAllNotes(notes));
    }

    void onNoteClicked(int noteId) {
        getView().showDetailedNote(noteId);
    }

    void onAddBtnClicked(String noteTitle, String noteDesc) {
        Note note = new Note(0 , noteTitle , noteDesc);
        repository.insertNote(note);
        getView().addNote(note);
    }

    public interface View extends BaseView{
        void showAllNotes(List<Note> notes);
        void showDetailedNote(final int noteId);
        void addNote(Note note);
    }

}
