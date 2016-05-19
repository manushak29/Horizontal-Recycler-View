package com.example.anna.retrofit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Forecast;
import model.Weather;
import recycler.DividerItemDecoration;
import recycler.RecyclerAdapter;
import remote.RemoteFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter mAdapter;
    private Context mContext = this;
    @BindView(R.id.horizontal_recycler)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(layoutManager);
        loadData();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha)));
    }

    public void loadData() {
        RemoteFactory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                //Call<Weather> body = response.body();
                List<Forecast> forecastList = response
                        .body()
                        .getQuery()
                        .getResults()
                        .getChannel()
                        .getItem()
                        .getForecast();

                mAdapter = new RecyclerAdapter(mContext, forecastList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sorry, request failed.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
