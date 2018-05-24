package com.ru.devit.notes.presentation.notedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ru.devit.notes.R;
import com.ru.devit.notes.data.NoteLocalRepository;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteApp;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BaseActivity;
import com.ru.devit.notes.presentation.utils.RandomColor;
import com.squareup.picasso.Picasso;

import java.io.File;

public class NoteDetailActivity extends BaseActivity implements NoteDetailPresenter.View , NoteEditDialog.NoteEditDialogListener {

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
    public void onPositiveBtnFromDialogClicked(String noteTitle , String noteDesc){
        presenter.onPositiveBtnFromDialogClicked(noteTitle , noteDesc);
    }

    @Override
    public void showNoteDetail(Note note){
        renderImage(note.getTitle());
        getToolbar().setTitle(note.getTitle());
        getToolbar().setBackgroundColor(RandomColor.getColor(note.getNoteColor()));
        mTextViewNoteTitle.setText(note.getTitle());
        mTextViewNoteDesc.setText(note.getDescription());
    }

    @Override
    public void openNoteEditDialog(){
        NoteEditDialog dialog = NoteEditDialog.newInstance(mTextViewNoteTitle.getText().toString(),
                                                           mTextViewNoteDesc.getText().toString());
        dialog.show(getSupportFragmentManager() , "TAG2");
    }

    @Override
    public void finishView(){
        Toast.makeText(this , getString(R.string.message_successfully_note_deleted) , Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDialogDismissed(){
        recreate();
    }

    @Override
    public void showNoteTitleNotBeEquals(){
        Toast.makeText(this , getString(R.string.message_note_title_not_equals) , Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessfullyNoteEdited() {
        Toast.makeText(this , getString(R.string.message_successfully_note_edited) , Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_note){
            presenter.onDeleteNoteAction();
        }
        if (id == R.id.action_edit_note){
            presenter.onEditNoteAction();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViews() {
        mImageViewNote = findViewById(R.id.iv_note_detail_background);
        mTextViewNoteTitle = findViewById(R.id.tv_note_detail_title);
        mTextViewNoteDesc = findViewById(R.id.tv_note_detail_desc);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
                .into(mImageViewNote);
    }

    private void initRepository() {
        NoteApp noteApp = (NoteApp) getApplicationContext();
        repository = new NoteLocalRepository(noteApp.getDatabase().noteDao());
    }
}
