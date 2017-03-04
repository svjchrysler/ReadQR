package org.gdgsantacruz.readqr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;


import org.gdgsantacruz.readqr.Helper;
import org.gdgsantacruz.readqr.api.IReadQR;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configInit();
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    private void configInit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Helper.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText().toString(), Toast.LENGTH_SHORT).show();
        verificarElemento(result.getText().toString());
        mScannerView.resumeCameraPreview(this);
    }

    private void verificarElemento(String data) {
        IReadQR iReadQR = retrofit.create(IReadQR.class);
        iReadQR.checkQR(data).enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
