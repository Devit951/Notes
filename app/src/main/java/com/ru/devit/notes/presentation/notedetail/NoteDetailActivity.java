package com.ru.devit.notes.presentation.notedetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ru.devit.notes.R;
import com.ru.devit.notes.data.NoteLocalRepository;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteApp;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.File;

public class NoteDetailActivity extends BaseActivity implements NoteDetailPresenter.View{

    private static final String NOTE_ID = "note_id";

    private ImageView mImageViewNote;
    private TextView mTextViewNoteTitle;
    private TextView mTextViewNoteDesc;
    private NoteRepository repository;
    private NoteDetailPresenter presenter;

    public static Intent makeIntent(Context context , final int noteId){
        Intent intent = new Intent(context , NoteDetailActivity.class);
        intent.putExtra(NOTE_ID , noteId);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_detail;
    }

    @Override
    public void showNoteDetail(Note note){
        renderImage(note.getTitle());
        getToolbar().setTitle(note.getTitle());
        Log.d("test" , "color = " + note.getNoteColor());
        getToolbar().setBackgroundColor(note.getNoteColor());
        mTextViewNoteTitle.setText(note.getTitle());
        mTextViewNoteDesc.setText(note.getDescription());
    }

    @Override
    protected void initViews() {
        mImageViewNote = findViewById(R.id.iv_note_detail_background);
        mTextViewNoteTitle = findViewById(R.id.tv_note_detail_title);
        mTextViewNoteDesc = findViewById(R.id.tv_note_detail_desc);
    }

    @Override
    protected void initPresenter() {
        initRepository();
        presenter = new NoteDetailPresenter(repository , getIntent().getIntExtra(NOTE_ID , 0));
        presenter.initView(this);
        presenter.subscribeToData();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void renderImage(String noteTitle){
        File directory = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/images");
        File myImageFile = new File(directory, noteTitle + ".jpg");
        Picasso.get()
                .load(myImageFile)
                .fit()
                .centerCrop()
                .into(mImageViewNote);
    }

    private void initRepository() {
        NoteApp noteApp = (NoteApp) getApplicationContext();
        repository = new NoteLocalRepository(noteApp.getDatabase().noteDao());
    }
}
