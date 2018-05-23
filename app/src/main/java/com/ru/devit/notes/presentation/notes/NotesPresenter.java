package com.ru.devit.notes.presentation.notes;

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
        addDisposable(repository.getAllNotes()
                .subscribe(notes -> getView().showAllNotes(notes)));
    }

    void onNoteClicked(int noteId) {
        getView().showDetailedNote(noteId);
    }

    void onAddBtnClicked(String noteTitle, String noteDesc , String imgPath , int noteColor) {
        if (!validateNote(noteTitle, noteDesc , imgPath)) {
            return;
        }
        Note note = new Note(0 , noteTitle , noteDesc , noteColor);
        note.setImagePath(imgPath);
        repository.insertNote(note);
        getView().addNote(note);
        getView().showSuccessfullyNoteAdded();
    }

    void onActionClearDatabase() {
        repository.clearDatabase();
        getView().notesCleared();
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
        void notesCleared();
        void showMessageTitleAndDescNotBeEmpty();
        void showSuccessfullyNoteAdded();
    }

}
