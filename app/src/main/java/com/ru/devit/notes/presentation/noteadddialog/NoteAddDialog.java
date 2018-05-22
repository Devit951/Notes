package com.ru.devit.notes.presentation.noteadddialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ru.devit.notes.R;

public class NoteAddDialog extends DialogFragment {

    private EditText mEditTextTitle , mEditTextDesc;
    private NoteAddDialogListener listener;

    public interface NoteAddDialogListener {
        void onAddBtnFromDialogClicked(String noteTitle , String noteDesc);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoteAddDialogListener) context;
        } catch (ClassCastException exception){
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_note , null);
        mEditTextTitle = view.findViewById(R.id.et_note_title);
        mEditTextDesc = view.findViewById(R.id.et_note_desc);

        builder.setView(view)
                .setTitle(getString(R.string.note_title))
                .setPositiveButton(getString(R.string.add), (dialogInterface, i) -> {
                    String noteTitle = mEditTextTitle.getText().toString();
                    String noteDesc = mEditTextDesc.getText().toString();
                    listener.onAddBtnFromDialogClicked(noteTitle, noteDesc);
                })
                .setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dismiss());

        return builder.create();
    }
}
