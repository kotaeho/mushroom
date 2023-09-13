package com.grandra.mushroom;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FungiApiService {
    @GET("fngsIlstrInfo")
    Call<FungiResponse> getFungiInfo(
            @Query("serviceKey") String serviceKey,
            @Query("q1") String q1
    );
}
