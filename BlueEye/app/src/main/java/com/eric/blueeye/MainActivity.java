package com.eric.blueeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eric.blueeye.service.BlueEyeService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "Blue Eye";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start_btn = findViewById(R.id.button);
        final Button end_btn = findViewById(R.id.button2);
        start_btn.setOnClickListener(this);
        end_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button:
                Log.d(TAG,"Start Btn clicked");
                start_service();
        }
    }

    private void start_service(){
        Intent intent = new Intent(this,BlueEyeService.class);
        startService(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }




}
