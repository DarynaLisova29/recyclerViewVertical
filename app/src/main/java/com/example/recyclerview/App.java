package com.example.recyclerview;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class App extends Application {
    static RecyclerView recyclerView;
    static LinearLayoutManager layoutManager;
    @Override
    public void onCreate() {
        super.onCreate();
        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
    }
}
