package com.raisac.medmob;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LookForDoctor extends AppCompatActivity {

    private String mPatientSufering;
    private Spinner mSufferingFrom;
    private BottomSheetDialog mBottomSheetDialog;
    private String[] mDep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_for_doctor);
    }

    public void emergncy(View view) {
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(R.layout.patient_request_form);
        Button requestDoctor = mBottomSheetDialog.findViewById(R.id.request_for_doctor);

        requestDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get text from the patient
                //get from the suffering_from information
                mSufferingFrom = findViewById(R.id.suffering_from);

                /* get information from the spinner and use it to match is to the doctors vailable online
                 * depnding on the inforation they have given*/

                mPatientSufering = mSufferingFrom.getSelectedItem().toString();
                /*
                 * get the location of the patient and triangulate it to search for a close by
                 * doctor that matches the patient' information of what they are suffering from

                 */

            }
        });

        mBottomSheetDialog.show();

    }
}
