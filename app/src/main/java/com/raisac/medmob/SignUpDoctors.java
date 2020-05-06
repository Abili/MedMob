package com.raisac.medmob;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpDoctors extends AppCompatActivity {

    private Button mSignUpDoctor;
    private ProgressBar mProgressBar;
    private String[] mDep;
    private ArrayAdapter<String> mMedmodDepartments;
    private Spinner mDepartments;
    private ArrayAdapter<String> mAdapterDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctors);
        mSignUpDoctor = findViewById(R.id.doctor_registerBtn);
        mProgressBar = findViewById(R.id.progressBar);
        mDepartments = findViewById(R.id.spinner_department);

        populateDepartment();

        mSignUpDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private void populateDepartment() {
        Resources department = getResources();
        mDep = department.getStringArray(R.array.departments);
        mAdapterDepartments = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, mDep);
        mDepartments.setAdapter(mAdapterDepartments);
        mAdapterDepartments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }
}
