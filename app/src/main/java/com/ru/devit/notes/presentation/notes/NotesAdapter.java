package com.ru.devit.notes.presentation.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ru.devit.notes.R;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.common.OnNoteClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final List<Note> notes;
    private final OnNoteClickListener onNoteClickListener;

    public NotesAdapter(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
        notes = new ArrayList<>();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note , parent , false);

        return new NoteViewHolder(view , onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    void addAll(List<Note> noteList){
        notes.addAll(noteList);
        notifyDataSetChanged();
    }

    void addNote(Note note){
        notes.add(note);
        notifyItemInserted(notes.size());
    }
}
