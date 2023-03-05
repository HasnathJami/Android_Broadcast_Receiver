package com.example.android_broadcast_receiver_checking_connectivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_broadcast_receiver_checking_connectivity.receivers.MyAlarmReceiver;
import com.example.android_broadcast_receiver_checking_connectivity.receivers.MyReceiver;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver MyReceiver = null;
    private Button startButton;
    private EditText text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver = new MyReceiver();
        broadcastIntent();
        initViewReference();
        initListener();
    }

    public void initViewReference() {
        startButton = findViewById(R.id.btn_start);
        text = findViewById(R.id.et_time);

    }

    public void initListener() {
        handleStartButton();
    }

    public void handleStartButton() {
        startButton.setOnClickListener(view -> {
            startAlert();
        });
    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    public void startAlert() {
        int i = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
    }

}