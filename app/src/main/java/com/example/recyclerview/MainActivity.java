package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        addScrollListener();
        post();

    }

    public void createList(List<Item>list){
        for (int i = 1; i < 51; i++) {
            list.add(new Item("Item " + i, (i == 20 || i == 30 || i == 40)));
        }
    }

    private void init(){
        createList(itemList);
        setContentView(R.layout.activity_main);
        App.recyclerView = findViewById(R.id.recycler);
        App.recyclerView.setLayoutManager(App.layoutManager);
        App.recyclerView.setAdapter(new MyAdapter(itemList));
    }

    private void centerItemsWithTrueValue(LinearLayoutManager layoutManager) {
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            Item item = itemList.get(i);
            if (item.isSelected()) {
                layoutManager.scrollToPositionWithOffset(i, (App.recyclerView.getWidth() - layoutManager.findViewByPosition(i).getWidth()) / 2);
                break;
            }
        }
    }

    private void addScrollListener(){
        App.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    centerItemsWithTrueValue(App.layoutManager);
                }
            }
        });
    }

    private void post(){
        App.recyclerView.post(new Runnable() {
            @Override
            public void run() {
                centerItemsWithTrueValue(App.layoutManager);
            }
        });
    }
}