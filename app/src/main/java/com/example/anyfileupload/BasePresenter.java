package com.example.anyfileupload;

public class BasePresenter<V> {
    protected V view;
    public void attachView(V view) {
        this.view = view;
    }
}
