package com.eric.blueeye;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eric.blueeye.service.ProfilerService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "Blue Eye";
    IntentService Profiler_Service = new ProfilerService();;
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
        final String cmd = getApplication().getString(R.string.ACTION_START_PROFILER);
        final String time_interval = getApplication().getString(R.string.time_interval);
        Intent intent = new Intent(this,ProfilerService.class);
        intent.setAction(cmd);
        intent.putExtra("TimeInterval",time_interval);
        startService(intent);
    }

}
