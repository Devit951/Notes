package com.ru.devit.notes.presentation.notes;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ru.devit.notes.R;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.common.OnNoteClickListener;
import com.ru.devit.notes.presentation.utils.RandomColor;
import com.squareup.picasso.Picasso;

import java.io.File;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mImageViewNote;
    private final TextView mTextViewTitle , mTextViewDescription;
    private final View mNoteStickyColor;
    private final OnNoteClickListener onNoteClickListener;

    NoteViewHolder(View itemView , OnNoteClickListener onNoteClickListener) {
        super(itemView);
        this.onNoteClickListener = onNoteClickListener;
        mImageViewNote = itemView.findViewById(R.id.iv_note);
        mTextViewTitle = itemView.findViewById(R.id.tv_note_title);
        mTextViewDescription = itemView.findViewById(R.id.tv_note_description);
        mNoteStickyColor = itemView.findViewById(R.id.colored_sticky);
    }

    void bind(Note note){
        onClickedNote(note.getNoteId());
        renderImage(note.getTitle());
        renderNoteColors(note.getNoteColor());
        mTextViewTitle.setText(note.getTitle());
        mTextViewDescription.setText(note.getDescription());
    }

    private void renderNoteColors(int noteColor) {
        mNoteStickyColor.setBackgroundColor(RandomColor.getColor(noteColor));
    }

    private void onClickedNote(int noteId){
        itemView.setOnClickListener(v -> onNoteClickListener.onNoteClicked(noteId));
    }

    private void renderImage(String noteImagePathTitle) {
        File directory = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/images");
        File myImageFile = new File(directory, noteImagePathTitle + ".jpg");
        Picasso.get()
                .load(myImageFile)
                .fit()
                .centerCrop()
                .into(mImageViewNote);
    }
}
