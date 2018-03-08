package com.sideeffect.app.retrofit.api;

import com.sideeffect.app.model.Medicine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User1 on 03-03-2018.
 */

public interface ApiServices {

    @GET("all_products_json_direct.php")
    Call<List<Medicine>> getData();

}
