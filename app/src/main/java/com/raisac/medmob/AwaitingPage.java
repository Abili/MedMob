package com.raisac.medmob;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AwaitingPage extends AppCompatActivity {

    private Switch mSwitchCompat;
    private FirebaseDatabase online;
    private DatabaseReference mReferenceOnline;
    private FirebaseAuth mAuth;
    private String mOffine;
    private String mOnline;
    private String mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awaiting_page);
        mReferenceOnline = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mUid = mAuth.getUid();

        mOffine = "OffLine";
        mOnline = "online";


        mSwitchCompat = findViewById(R.id.onlineSwitch);
        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    mSwitchCompat.setText(mOnline);
                    mSwitchCompat.setTextColor(Color.GREEN);
                    mReferenceOnline
                            .child("users")
                            .child("doctors")
                            .child(mUid)
                            .child("status")
                            .setValue(mOnline);

                } else {

                    mSwitchCompat.setText(mOffine);
                    mSwitchCompat.setTextColor(Color.WHITE);
                    mReferenceOnline
                            .child("users")
                            .child("doctors")
                            .child(mUid)
                            .child("status")
                            .setValue(mOffine);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSwitchCompat.setText(mOffine);
        mSwitchCompat.setTextColor(Color.WHITE);
        mReferenceOnline
                .child("users")
                .child("doctors")
                .child(mUid)
                .child("status")
                .setValue(mOffine);
    }
}
