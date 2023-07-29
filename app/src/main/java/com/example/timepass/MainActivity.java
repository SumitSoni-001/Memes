package com.example.timepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.timepass.API.ApiController;
import com.example.timepass.API.ApiInterface;
import com.example.timepass.Adapters.MemeAdapter;
import com.example.timepass.Models.ApiModel;
import com.example.timepass.Models.MemeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView memeRCV;
    MemeAdapter adapter;
    Retrofit retrofit;
    ApiInterface apiInterface;
    List<ApiModel> list;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = ApiController.getRetrofit();
        apiInterface = retrofit.create(ApiInterface.class);

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.refresh);

        memeRCV = findViewById(R.id.memeRCV);
        list = new ArrayList<>();
        adapter = new MemeAdapter(this, list);
        memeRCV.setLayoutManager(new LinearLayoutManager(this));
        memeRCV.setAdapter(adapter);

        OnLayoutRefreshListener();

    }

    public void findMeme() {
        refreshLayout.setRefreshing(true);

        apiInterface.getMeme().enqueue(new Callback<MemeModel>() {
            @Override
            public void onResponse(Call<MemeModel> call, Response<MemeModel> response) {
                if (response.isSuccessful()) {

                    if (!list.isEmpty()) {
                        list.clear();
                    }

                    Log.d("success", response.body().getMemes().toString());
                    list.addAll(response.body().getMemes());
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<MemeModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRefresh() {
        findMeme();
    }

    private void OnLayoutRefreshListener(){
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                findMeme();
            }
        });
    }
}