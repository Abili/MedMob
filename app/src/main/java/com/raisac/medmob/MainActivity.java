package com.raisac.medmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRegisterButton;
    private EditText mPhoneNumber;
    boolean isEnabled = false;
    private String mButtonString;
    private EditText mVerifcatioCode;
    private Button mVerify;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mRegisterButton = findViewById(R.id.doctorSignUp);
        mVerify = findViewById(R.id.doctorVerify);
        mVerifcatioCode = findViewById(R.id.verificationCode);
        mButtonString = mRegisterButton.getText().toString();

        mRegisterButton.setOnClickListener(this);
        mVerify.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.doctorSignUp){
            if(mPhoneNumber.getText().toString().startsWith("256") &&
                    mPhoneNumber.getText().toString().length()==12) {
                mPhoneNumber.setEnabled(isEnabled);
                mVerify.setVisibility(View.VISIBLE);
                mRegisterButton.setVisibility(View.INVISIBLE);
            }else {
                mPhoneNumber.setError("Enter Valid phone Number");
            }
        }
        else if(id == R.id.doctorVerify){
            if (!mVerifcatioCode.getText().toString().equals("")) {
                startActivity(new Intent(MainActivity.this, SignUpDoctors.class));
            } else {
                mVerifcatioCode.setError("Invalid Verification code");
            }
        }
    }
}
