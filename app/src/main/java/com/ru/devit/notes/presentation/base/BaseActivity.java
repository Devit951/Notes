package com.ru.devit.notes.presentation.base;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ru.devit.notes.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolbar();
        initViews();
        initPresenter();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initToolbar(){
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
        }
    }

    protected Toolbar getToolbar(){
        return mToolbar;
    }

    protected abstract int getLayoutId();
    protected abstract void initViews();
    protected abstract void initPresenter();
}