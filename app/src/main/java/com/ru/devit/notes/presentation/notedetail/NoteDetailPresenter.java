package com.ru.devit.notes.presentation.notedetail;

import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BasePresenter;
import com.ru.devit.notes.presentation.base.BaseView;

public class NoteDetailPresenter extends BasePresenter<NoteDetailPresenter.View> {

    private Note note;
    private final NoteRepository repository;
    private final int noteId;

    NoteDetailPresenter(NoteRepository repository , int noteId) {
        this.repository = repository;
        this.noteId = noteId;
    }

    void subscribeToData() {
        addDisposable(repository.getNoteById(noteId)
                .subscribe(note -> {
                    this.note = note;
                    getView().showNoteDetail(note);
                        } ,
                        Throwable::printStackTrace));
    }

    void onDeleteNoteAction() {
        repository.deleteNoteById(noteId);
        getView().finishView();
    }

    void onEditNoteAction() {
        getView().openNoteEditDialog();
    }

    void onPositiveBtnFromDialogClicked(String noteTitle, String noteDesc) {
        if (!note.getTitle().equals(noteTitle)){
            repository.updateNoteById(noteId , noteTitle , noteDesc);
            getView().showSuccessfullyNoteEdited();
        } else {
            getView().showNoteTitleNotBeEquals();
        }
    }


    public interface View extends BaseView{
        void showNoteDetail(Note note);
        void finishView();
        void openNoteEditDialog();
        void showNoteTitleNotBeEquals();
        void showSuccessfullyNoteEdited();
    }
}
