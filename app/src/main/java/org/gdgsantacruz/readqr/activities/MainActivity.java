package org.gdgsantacruz.readqr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import org.gdgsantacruz.readqr.models.User;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        configInit();
    }

    private void configInit() {
       firebaseDatabase = FirebaseDatabase.getInstance();
        cargarData();
    }


    private void cargarData() {

        imprimirDates();
    }

    private void imprimirDates() {
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //User user = dataSnapshot.getValue(User.class);
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    data.getValue(User.class);
                    Log.e("data", data.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        Log.e("Error", result.getText().toString());
        Toast.makeText(this, result.getText().toString(), Toast.LENGTH_SHORT).show();
        mScannerView.resumeCameraPreview(this);
    }
}
