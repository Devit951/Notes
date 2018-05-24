package com.ru.devit.notes.presentation.base;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<View extends BaseView> {
    private View view;
    private final List<Disposable> disposables = new ArrayList<>();

    public void initView(View view){
        this.view = view;
    }

    public void addDisposable(Disposable disposable){
        disposables.add(disposable);
    }

    public void onDestroy(){
        this.view = null;
        for (Disposable disposable : disposables){
            disposable.dispose();
        }
    }

    public View getView() {
        return view;
    }
}
