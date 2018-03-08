package com.sideeffect.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.sideeffect.app.adapter.SimpleObjectAdapter;
import com.sideeffect.app.database.AppDatabase;
import com.sideeffect.app.databinding.ActivityMainBinding;
import com.sideeffect.app.model.Medicine;
import com.sideeffect.app.retrofit.api.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    ProgressDialog pDialog;
    Context mContext;
    SimpleObjectAdapter adapter;
    List<Medicine> medi_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        intitview();
    }

    private void intitview() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please Wait");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
        parsejson();
    }

    private void parsejson() {
        pDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices redditAPI = retrofit.create(ApiServices.class);
        Call<List<Medicine>> call = redditAPI.getData();

        call.enqueue(new Callback<List<Medicine>>() {

            @Override
            public void onResponse(Call<List<Medicine>> call, Response<List<Medicine>> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: received information: " + response.body().toString());
                medi_data = response.body();
                //Toast.makeText(mContext, "" + medi_data.size(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Size: " + medi_data.size());
                AppDatabase.getAppDatabase(MainActivity.this).mediDao().insertAll(medi_data);

                if (response.isSuccessful()) {
                    for (int i = 0; i < medi_data.size(); i++) {
                        //medi_data.get(i).getEntity_id();
                        Log.d("EntityName", medi_data.get(i).getEntity_id());
                        /*String entity_id, String name, String price, String oldprice*/
                        /*medi_data.add(new Medicine(medi_data.get(i).getEntity_id(),
                                medi_data.get(i).getName(),
                                medi_data.get(i).getPrice(),
                                medi_data.get(i).getOldprice()));*/
                    }
                }

                inflateadapter();
            }

            @Override
            public void onFailure(Call<List<Medicine>> call, Throwable t) {
            }
        });
    }

    private void inflateadapter() {
        adapter = new SimpleObjectAdapter(mContext, medi_data);
        binding.rvRecycle.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRecycle.setAdapter(adapter);
        pDialog.dismiss();

    }
}
