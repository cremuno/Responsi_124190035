package com.prakpm.a124190035_responsi_mobile.rujukan_rs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prakpm.a124190035_responsi_mobile.R;
import com.prakpm.a124190035_responsi_mobile.MainActivity;
import com.prakpm.a124190035_responsi_mobile.modul.response_faskes.DataItem;
import com.prakpm.a124190035_responsi_mobile.modul.response_faskes.FaskesMap;
import com.prakpm.a124190035_responsi_mobile.service.ApiMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RujukanRSActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static BottomNavigationView navbar;
    private RecyclerView recyclerView;
    private RujukanRSAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rujukan_rs);

        navbar = findViewById(R.id.bottomNavigation);
        navbar.setOnNavigationItemSelectedListener(this);
        navbar.setSelectedItemId(R.id.ic_rsRujukan);

        recyclerView = findViewById(R.id.rv_rsRujukan);
        adapter = new RujukanRSAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Call<FaskesMap> call = ApiMain.covidAPIService().getDataFaskes("");

        call.enqueue(new Callback<FaskesMap>() {
            @Override
            public void onResponse(Call<FaskesMap> call, Response<FaskesMap> responseFaskes) {
                if(responseFaskes.isSuccessful()){

                    List<DataItem> faskes = responseFaskes.body().getData();
                    Log.d("data", responseFaskes.body().toString());
                    if (!faskes.isEmpty()){
                        adapter.setData(faskes);
                    }
                }
            }

            @Override
            public void onFailure(Call<FaskesMap> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_kasusCovid:
                Intent i = new Intent(RujukanRSActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;

            case R.id.ic_rsRujukan:
                break;

        }
        return true;
    }
}