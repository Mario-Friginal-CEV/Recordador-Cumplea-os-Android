package com.example.apps1t.recordadordecumpleanos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RaspberryAPI {
    @GET("today.php")
    Call <String> raspberryCall();
}
