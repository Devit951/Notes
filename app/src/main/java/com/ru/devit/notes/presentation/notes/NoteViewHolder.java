package com.ru.devit.notes.presentation.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ru.devit.notes.R;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.common.OnNoteClickListener;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mImageViewNote;
    private final TextView mTextViewTitle , mTextViewDescription;
    private final OnNoteClickListener onNoteClickListener;

    public NoteViewHolder(View itemView , OnNoteClickListener onNoteClickListener) {
        super(itemView);
        this.onNoteClickListener = onNoteClickListener;
        mImageViewNote = itemView.findViewById(R.id.iv_note);
        mTextViewTitle = itemView.findViewById(R.id.tv_note_title);
        mTextViewDescription = itemView.findViewById(R.id.tv_note_description);
    }

    void bind(Note note){
        onClickedNote(note.getNoteId());
        mTextViewTitle.setText(note.getTitle());
        mTextViewDescription.setText(note.getDescription());
    }

    void onClickedNote(int noteId){
        itemView.setOnClickListener(v -> onNoteClickListener.onNoteClicked(noteId));
    }
}
