package com.prakpm.a124190035_responsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prakpm.a124190035_responsi_mobile.kasus_harian.KasusCovidAdapter;
import com.prakpm.a124190035_responsi_mobile.modul.response_status.Content;
import com.prakpm.a124190035_responsi_mobile.modul.response_status.ResponseDataHarian;
import com.prakpm.a124190035_responsi_mobile.rujukan_rs.RujukanRSActivity;
import com.prakpm.a124190035_responsi_mobile.service.ApiMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView navbar;
    private KasusCovidAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navbar = findViewById(R.id.bottomNavigation);
        navbar.setOnNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.rv_kasusCovid);
        adapter = new KasusCovidAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
        recyclerView.setAdapter(adapter);
        getKasusHarian();

    }

    private void getKasusHarian() {

        Call<ResponseDataHarian> call = ApiMain.covidAPIService().getDataHarian("");

        call.enqueue(new Callback<ResponseDataHarian>() {
            @Override
            public void onResponse(Call<ResponseDataHarian> call, Response<ResponseDataHarian> responseDataHarian) {
                if(responseDataHarian.isSuccessful()){
                    Toast.makeText(MainActivity.this, "berhasil", Toast.LENGTH_SHORT).show();

                    List<Content> kasusHarian = responseDataHarian.body().getData().getContent();
                    Log.d("data", responseDataHarian.body().toString());
                    if (!kasusHarian.isEmpty()){
                        adapter.setData(kasusHarian);
                        recyclerView.scrollToPosition(adapter.getItemCount()-1);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDataHarian> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_kasusCovid:
                break;

            case R.id.ic_rsRujukan:
                Intent i = new Intent(MainActivity.this, RujukanRSActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;
        }
        return true;
    }

}