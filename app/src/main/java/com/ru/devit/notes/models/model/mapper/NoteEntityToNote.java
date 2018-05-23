package com.ru.devit.notes.models.model.mapper;

import com.ru.devit.notes.models.db.NoteEntity;
import com.ru.devit.notes.models.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteEntityToNote {

    public Note toNote(NoteEntity noteEntity){
        final int noteId = noteEntity.getNoteId();
        final String title = noteEntity.getTitle();
        final String desc = noteEntity.getDescription();
        final int noteColor = noteEntity.getNoteColor();
        final String imgPaths = noteEntity.getImgPath();
        Note note = new Note(noteId, title, desc , noteColor);
        note.setImagePath(imgPaths);
        return note;
    }

    public List<Note> toNoteList(List<NoteEntity> entities){
        List<Note> notes = new ArrayList<>();
        for (NoteEntity noteEntity : entities){
            final int noteId = noteEntity.getNoteId();
            final String title = noteEntity.getTitle();
            final String desc = noteEntity.getDescription();
            final int noteColor = noteEntity.getNoteColor();
            final String imgPaths = noteEntity.getImgPath();
            final Note note = new Note(noteId , title , desc , noteColor);
            note.setImagePath(imgPaths);
            notes.add(note);
        }
        return notes;
    }

    public NoteEntity fromNote(Note note){
        final int noteId = note.getNoteId();
        final String title = note.getTitle();
        final String desc = note.getDescription();
        final int noteColor = note.getNoteColor();
        final String imgPaths = note.getImagePath();
        NoteEntity noteEntity = new NoteEntity(noteId, title, desc , noteColor);
        noteEntity.setImgPath(imgPaths);
        return noteEntity;
    }
}
