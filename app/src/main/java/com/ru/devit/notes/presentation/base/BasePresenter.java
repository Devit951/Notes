package com.ru.devit.notes.presentation.base;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<View extends BaseView> {
    private View view;
    private Disposable disposable;

    public void initView(View view){
        this.view = view;
    }

    public void addDisposable(Disposable disposable){
        this.disposable = disposable;
    }

    public void onDestroy(){
        this.view = null;
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public View getView() {
        return view;
    }
}
