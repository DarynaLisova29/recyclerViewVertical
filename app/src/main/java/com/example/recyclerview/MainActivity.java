package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<Item> itemList = new ArrayList<>();
    Button rightBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        addScrollListener();
        post();
        setClickButton();

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rightBtn) {
            int position=searchIsSelected();
            Log.d("Position", "onClick: "+position);
            if(position>-1){
                scrollToSpecificItem(position);
            }
        }
    }


    public void createList(List<Item>list){
        for (int i = 1; i < 51; i++) {
            list.add(new Item("Item " + i, (i == 20 || i == 30 || i == 40)));
        }
    }

    private void init(){
        createList(itemList);

        setContentView(R.layout.activity_main);

        rightBtn = findViewById(R.id.rightBtn);
        App.recyclerView = findViewById(R.id.recycler);
        App.recyclerView.setLayoutManager(App.layoutManager);
        App.recyclerView.setAdapter(new MyAdapter(itemList));
    }

    private void setClickButton() {
        rightBtn.setOnClickListener(this);
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
    }private int searchIsSelected(){
        int firstVisibleItemPosition = App.layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = App.layoutManager.findLastVisibleItemPosition();
        for(int i=firstVisibleItemPosition;i<lastVisibleItemPosition;i++){
            if(itemList.get(i).isSelected()){
                firstVisibleItemPosition=i+1;
            }
        }
        for(int i=firstVisibleItemPosition;i<itemList.size();i++){
            if(itemList.get(i).isSelected()){
                return i;
            }
        }
        return -1;
    }

    private void scrollToSpecificItem(int targetPosition) {
        App.recyclerView.scrollToPosition(targetPosition);
        App.recyclerView.post(new Runnable() {
            @Override
            public void run() {
                View view = App.layoutManager.findViewByPosition(targetPosition);
                if (view != null) {
                    App.layoutManager.scrollToPositionWithOffset(targetPosition, (App.recyclerView.getWidth() - view.getWidth()) / 2);
                } else {
                    App.recyclerView.post(this);
                }
            }
        });
    }

    }