package com.ru.devit.notes.presentation.notedetail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ru.devit.notes.R;
import com.ru.devit.notes.data.ImageSaverToDisk;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class NoteEditDialog extends DialogFragment {
    private View mRootView;
    private EditText mEditTextTitle , mEditTextDesc;
    private ImageView mImageViewFromGallery;
    private NoteEditDialogListener listener;
    private ImageSaverToDisk imageSaverToDisk;
    private Uri resultImagePath;
    private final int REQUEST_CODE_MULTIPLY_IMAGE = 88;
    private static final String EXTRA_NOTE_DESC = "extra_note_desc";
    private static final String EXTRA_NOTE_TITLE = "extra_note_title";

    public static NoteEditDialog newInstance(String noteTitle , String noteDesc){
        NoteEditDialog dialog = new NoteEditDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NOTE_TITLE , noteTitle);
        bundle.putString(EXTRA_NOTE_DESC , noteDesc);
        dialog.setArguments(bundle);
        return dialog;
    }

    public interface NoteEditDialogListener{
        void onPositiveBtnFromDialogClicked(String noteTitle , String noteDesc);
        void onDialogDismissed();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        renderNoteImageView(getArguments().getString(EXTRA_NOTE_TITLE));
        setNoteTitle(getArguments().getString(EXTRA_NOTE_TITLE));
        setNoteDesc(getArguments().getString(EXTRA_NOTE_DESC));
        mImageViewFromGallery.setOnClickListener(v -> selectPictureFromGallery());
        builder.setView(mRootView)
                .setTitle(getString(R.string.edit_note))
                .setPositiveButton(getString(R.string.edit), (dialogInterface, i) -> {
                    String noteTitle = mEditTextTitle.getText().toString();
                    String noteDesc = mEditTextDesc.getText().toString();
                    saveImage(noteTitle);
                    listener.onPositiveBtnFromDialogClicked(noteTitle , noteDesc);
                })
                .setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dismiss());
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        listener.onDialogDismissed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_MULTIPLY_IMAGE){
            if (data != null){
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null){
                    resultImagePath = selectedImageUri;
                    renderImage(resultImagePath);
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoteEditDialogListener) context;
        } catch (ClassCastException exception){
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_note , null);
        mEditTextTitle = mRootView.findViewById(R.id.et_note_title);
        mEditTextDesc = mRootView.findViewById(R.id.et_note_desc);
        mImageViewFromGallery = mRootView.findViewById(R.id.iv_image_from_gallery);
        Button mBtnPickUpFromGallery = mRootView.findViewById(R.id.btn_pick_up_image_from_gallery);
        mBtnPickUpFromGallery.setVisibility(View.GONE);
        mImageViewFromGallery.setVisibility(View.VISIBLE);
        imageSaverToDisk = new ImageSaverToDisk();
    }

    private void renderNoteImageView(String noteTitle){
        renderImage(noteTitle);
    }

    private void setNoteTitle(String noteTitle){
        mEditTextTitle.setText(noteTitle);
    }

    private void setNoteDesc(String noteDesc){
        mEditTextDesc.setText(noteDesc);
    }

    private void renderImage(Uri imgPath){
        Picasso.get()
                .load(imgPath)
                .fit()
                .centerCrop()
                .into(mImageViewFromGallery);
    }

    private void saveImage(String noteTitle) {
        try {
            if (resultImagePath != null){
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver() ,
                        resultImagePath);
                imageSaverToDisk.saveImage(bitmap , noteTitle).subscribe();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renderImage(String noteTitle){
        File directory = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/images");
        File myImageFile = new File(directory, noteTitle + ".jpg");
        Picasso.get()
                .load(myImageFile)
                .fit()
                .centerCrop()
                .into(mImageViewFromGallery);
    }

    private void selectPictureFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent , getString(R.string.select_image)) , REQUEST_CODE_MULTIPLY_IMAGE);
    }


}
