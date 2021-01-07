package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.manoranjan.citizenportal.Adaptor.TimelineAdapter;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FollowUpGraphActivity extends AppCompatActivity {
    public static final String ORIENTATION = "orientation";
    LinearLayout progresslayout;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_graph);
        progresslayout=findViewById(R.id.progresslayout);
        int orientation = LinearLayoutManager.VERTICAL;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, orientation, false));
        TimelineAdapter adapter = new TimelineAdapter(orientation, getItems());
        recycler.setAdapter(adapter);

    }

    private List<ListItem> getItems() {
        List<ListItem> items = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            ListItem item = new ListItem();
            item.setName("Client number " + i);
            item.setAddress("Some address " + random.nextInt(100));
            items.add(item);
        }
        progresslayout.setVisibility(View.GONE);
        return items;
    }

}
