package com.prakpm.a124190035_responsi_mobile.service;

import com.prakpm.a124190035_responsi_mobile.modul.response_faskes.FaskesMap;
import com.prakpm.a124190035_responsi_mobile.modul.response_kumulatif.KumulatifResponse;
import com.prakpm.a124190035_responsi_mobile.modul.response_status.ResponseDataHarian;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRepository {
    @GET("rekapitulasi_v2/jabar/harian")
    Call<ResponseDataHarian> getDataHarian(@Query("level") String query);
    @GET("sebaran_v2/jabar/faskes")
    Call<FaskesMap> getDataFaskes(@Query("tipe") String query);
    @GET("rekapitulasi_v2/jabar/kumulatif")
    Call<KumulatifResponse> getDataKumulatif(@Query("level") String query);



}
