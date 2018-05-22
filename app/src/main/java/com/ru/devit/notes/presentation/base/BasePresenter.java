package com.ru.devit.notes.presentation.base;

public abstract class BasePresenter<View extends BaseView> {
    private View view;

    public void initView(View view){
        this.view = view;
    }

    public void onDestroy(){
        this.view = null;
    }

    public View getView() {
        return view;
    }
}
