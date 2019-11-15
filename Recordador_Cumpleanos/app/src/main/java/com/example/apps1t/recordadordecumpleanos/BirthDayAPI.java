package com.example.apps1t.recordadordecumpleanos;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BirthDayAPI {
    @GET("bdays")
    Call<ArrayList<Birthday>> getBirthdays();

    @FormUrlEncoded
    @POST("bday")
    Call<String> postBirthday(@Field("name") String name, @Field("image") String image, @Field("date") long date);
}
