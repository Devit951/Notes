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
        return new Note(noteId, title, desc , noteColor);
    }

    public List<Note> toNoteList(List<NoteEntity> entities){
        List<Note> notes = new ArrayList<>();
        for (NoteEntity noteEntity : entities){
            final int noteId = noteEntity.getNoteId();
            final String title = noteEntity.getTitle();
            final String desc = noteEntity.getDescription();
            final int noteColor = noteEntity.getNoteColor();
            final Note note = new Note(noteId , title , desc , noteColor);
            notes.add(note);
        }
        return notes;
    }

    public NoteEntity fromNote(Note note){
        final int noteId = note.getNoteId();
        final String title = note.getTitle();
        final String desc = note.getDescription();
        final int noteColor = note.getNoteColor();
        return new NoteEntity(noteId, title, desc , noteColor);
    }
}
