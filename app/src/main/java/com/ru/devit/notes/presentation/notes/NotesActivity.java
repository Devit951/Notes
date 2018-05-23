package com.ru.devit.notes.presentation.notes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ru.devit.notes.R;
import com.ru.devit.notes.data.NoteLocalRepository;
import com.ru.devit.notes.models.model.Note;
import com.ru.devit.notes.presentation.NoteApp;
import com.ru.devit.notes.presentation.NoteRepository;
import com.ru.devit.notes.presentation.base.BaseActivity;
import com.ru.devit.notes.presentation.noteadddialog.NoteAddDialog;
import com.ru.devit.notes.presentation.notedetail.NoteDetailActivity;

import java.util.List;

public class NotesActivity extends BaseActivity implements NotesPresenter.View , NoteAddDialog.NoteAddDialogListener {

    private FloatingActionButton mFABAddNote;
    private RecyclerView mRecyclerViewNotes;
    private NotesAdapter adapter;
    private NoteRepository repository;
    private NotesPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notes;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFABAddNote.setOnClickListener(v -> {
            NoteAddDialog noteAddDialog = new NoteAddDialog();
            noteAddDialog.show(getSupportFragmentManager() , "TAG");
        });
    }

    @Override
    protected void onStop() {
        mFABAddNote.setOnClickListener(null);
        super.onStop();
    }

    @Override
    public void onAddBtnFromDialogClicked(String noteTitle, String noteDesc , String imgPath , int noteColor) {
        presenter.onAddBtnClicked(noteTitle , noteDesc , imgPath , noteColor);
    }

    @Override
    public void showAllNotes(List<Note> notes) {
        adapter.addAll(notes);
    }

    @Override
    public void showDetailedNote(int noteId) {
        Intent intent = NoteDetailActivity.makeIntent(NotesActivity.this , noteId);
        startActivity(intent);
    }

    @Override
    public void addNote(Note note){
        adapter.addNote(note);
    }

    @Override
    public void notesCleared(){
        adapter.clearNotes();
        Snackbar snackbar = Snackbar
                .make(mRecyclerViewNotes ,
                        getString(R.string.message_successfully_notes_deleted) ,
                        Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
        snackbar.show();
    }

    @Override
    public void showMessageTitleAndDescNotBeEmpty(){
        Snackbar.make(mFABAddNote , getString(R.string.message_error_note) , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessfullyNoteAdded(){
        Snackbar snackbar = Snackbar
                .make(mRecyclerViewNotes ,
                        getString(R.string.message_successfully_note_added) ,
                        Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear_database){
            presenter.onActionClearDatabase();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViews() {
        mFABAddNote = findViewById(R.id.fab_add_note);
        mRecyclerViewNotes = findViewById(R.id.rv_notes);
        initAdapter();
    }

    @Override
    protected void initPresenter() {
        initRepository();
        presenter = new NotesPresenter(repository);
        presenter.initView(this);
        presenter.subscribeToData();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initAdapter() {
        adapter = new NotesAdapter(noteId -> presenter.onNoteClicked(noteId));
        mRecyclerViewNotes.setAdapter(adapter);
        mRecyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRepository() {
        NoteApp noteApp = (NoteApp) getApplicationContext();
        repository = new NoteLocalRepository(noteApp.getDatabase().noteDao());
    }
}
