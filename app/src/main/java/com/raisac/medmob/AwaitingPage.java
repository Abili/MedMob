package com.raisac.medmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class AwaitingPage extends AppCompatActivity {

    private Switch mSwitchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awaiting_page);
        mSwitchCompat = findViewById(R.id.onlineSwitch);
        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mSwitchCompat.setText("OnLine");
                    mSwitchCompat.setTextColor(Color.GREEN);
                }
                else {
                    mSwitchCompat.setText("OffLine");
                    mSwitchCompat.setTextColor(Color.WHITE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSwitchCompat.setText("OffLine");
        mSwitchCompat.setTextColor(Color.WHITE);
    }
}
