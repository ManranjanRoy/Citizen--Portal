package com.manoranjan.citizenportal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Adaptor.DocumentsListAdaptor;
import com.manoranjan.citizenportal.Adaptor.TimelineAdapter;
import com.manoranjan.citizenportal.Api.RequestData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.DocumentListResponse;
import com.manoranjan.citizenportal.Response.SingleFollowupGraphResponse;
import com.manoranjan.citizenportal.model.ListItem;
import com.manoranjan.citizenportal.service.CountryService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FollowUpGraphActivity extends AppCompatActivity {
    public static final String ORIENTATION = "orientation";
    LinearLayout progresslayout;
    RecyclerView recycler;
    List<SingleFollowupGraphResponse> singleFollowupGraphResponseList=new ArrayList<>();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_graph);
        progresslayout=findViewById(R.id.progresslayout);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getfolloeup();

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

    public void getfolloeup() {
        JsonObject obj = RequestData.getsinglefollwupchart();
        progresslayout.setVisibility(View.VISIBLE);
        CountryService countryService = new CountryService();
        countryService.getAPI().getsinglefollowupdetails(obj).enqueue(new Callback<List<SingleFollowupGraphResponse>>() {
            @Override
            public void onResponse(Call<List<SingleFollowupGraphResponse>> call, Response<List<SingleFollowupGraphResponse>> response) {
                Log.d("response", response.body().toString());
                progresslayout.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        singleFollowupGraphResponseList = response.body();
                        Collections.reverse(singleFollowupGraphResponseList);
                        int orientation = LinearLayoutManager.VERTICAL;
                        TimelineAdapter adapter = new TimelineAdapter(getApplicationContext(), singleFollowupGraphResponseList);
                        recycler.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error 22", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SingleFollowupGraphResponse>> call, Throwable t) {
                progresslayout.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "error 1" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
