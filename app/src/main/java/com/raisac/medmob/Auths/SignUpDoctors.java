package com.raisac.medmob.Auths;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.raisac.medmob.R;

import java.util.Calendar;

public class SignUpDoctors extends AppCompatActivity {

    private Button mSignUpDoctor;
    private ProgressBar mProgressBar;
    private String[] mDep;
    private ArrayAdapter<String> mMedmodDepartments;
    private Spinner mDepartments, mExperience;
    private String[] mExp;
    private String[] age;
    private String[] mAge;
    private Spinner mAgeSpinner;
    private EditText mEdDoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctors);
        mSignUpDoctor = findViewById(R.id.doctor_registerBtn);
        mProgressBar = findViewById(R.id.progressBar);
        mDepartments = findViewById(R.id.spinner_department);
        mExperience = findViewById(R.id.spinner_experience);

        populateDepartment();
        populateExperience();
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
        ArrayAdapter<String> adapterDepartments = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, mDep);
        mDepartments.setAdapter(adapterDepartments);
        adapterDepartments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void populateExperience() {
        Resources experinece = getResources();
        mExp = experinece.getStringArray(R.array.experience);
        ArrayAdapter<String> adapterExperince = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, mExp);
        mExperience.setAdapter(adapterExperince);
        adapterExperince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void dateOfBirth(View view) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(calendar.MONTH);
        int date = calendar.get(calendar.DAY_OF_MONTH);
        int year = calendar.get(calendar.YEAR);

        //create date picker dialog;
        DatePickerDialog pickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mEdDoB = findViewById(R.id.dateOfBirth);
                mEdDoB.setText(String.format("%d/%d/%d", dayOfMonth, month, year));

            }
        }, year, month, date);
        pickerDialog.show();
    }


    }


