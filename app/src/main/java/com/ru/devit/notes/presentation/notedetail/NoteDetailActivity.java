package com.ru.devit.notes.presentation.notedetail;

import android.content.Context;
import android.content.Intent;

import com.ru.devit.notes.R;
import com.ru.devit.notes.presentation.base.BaseActivity;

public class NoteDetailActivity extends BaseActivity{

    private static final String NOTE_ID = "note_id";

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
    protected void initViews() {

    }

    @Override
    protected void initPresenter() {

    }
}
