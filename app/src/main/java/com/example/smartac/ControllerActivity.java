package com.example.smartac;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControllerActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Integer temp1 = 0;
    Integer temp2 = 0;
    Boolean status1 = false;
    Boolean status2 = false;
    private TextView txtTemp1;
    private TextView txtTemp2;
    private Switch switch1;
    private Switch switch2;
    private ProgressBar progressBar;
    private Switch followTimetable1;
    private Switch followTimetable2;
    private Switch autoTemp1;
    private Switch autoTemp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_controller);
        Toolbar toolbar = findViewById(R.id.toolbar_controller);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Initialize components
        txtTemp1 = findViewById(R.id.temp1);
        txtTemp2 = findViewById(R.id.temp2);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        progressBar = findViewById(R.id.progressBar);
        followTimetable1 = findViewById(R.id.switch3);
        autoTemp1 = findViewById(R.id.switch4);
        followTimetable2 = findViewById(R.id.switch5);
        autoTemp2 = findViewById(R.id.switch6);

        progressBar.setVisibility(View.VISIBLE);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temp1 = Integer.parseInt(dataSnapshot.child("Aircond").child("Airc001").child("Temp").getValue(String.class));
                txtTemp1.setText(String.valueOf(temp1));
                temp2 = Integer.parseInt(dataSnapshot.child("Aircond").child("Airc002").child("Temp").getValue(String.class));
                txtTemp2.setText(String.valueOf(temp2));

                if (dataSnapshot.child("Aircond").child("Airc001").child("Status").getValue(String.class).equals("On")) {
                    switch1.setChecked(true);
                } else {
                    switch1.setChecked(false);
                }

                if (dataSnapshot.child("Aircond").child("Airc002").child("Status").getValue(String.class).equals("On")) {
                    switch2.setChecked(true);
                } else {
                    switch2.setChecked(false);
                }

                if (dataSnapshot.child("Aircond").child("Airc001").child("FollowTimetable").getValue(String.class).equals("True")) {
                    followTimetable1.setChecked(true);
                } else {
                    followTimetable1.setChecked(false);
                }

                if (dataSnapshot.child("Aircond").child("Airc001").child("AutoTemp").getValue(String.class).equals("True")) {
                    autoTemp1.setChecked(true);
                } else {
                    autoTemp1.setChecked(false);
                }

                if (dataSnapshot.child("Aircond").child("Airc002").child("FollowTimetable").getValue(String.class).equals("True")) {
                    followTimetable2.setChecked(true);
                } else {
                    followTimetable2.setChecked(false);
                }

                if (dataSnapshot.child("Aircond").child("Airc002").child("AutoTemp").getValue(String.class).equals("True")) {
                    autoTemp2.setChecked(true);
                } else {
                    autoTemp2.setChecked(false);
                }

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch1.setChecked(isChecked);
                if (isChecked)
                    myRef.child("Aircond").child("Airc001").child("Status").setValue("On");
                else
                    myRef.child("Aircond").child("Airc001").child("Status").setValue("Off");
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch2.setChecked(isChecked);

                if (isChecked)
                    myRef.child("Aircond").child("Airc002").child("Status").setValue("On");
                else
                    myRef.child("Aircond").child("Airc002").child("Status").setValue("Off");
            }
        });

        autoTemp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                autoTemp1.setChecked(isChecked);
                if (isChecked)
                    myRef.child("Aircond").child("Airc001").child("AutoTemp").setValue("True");
                else
                    myRef.child("Aircond").child("Airc001").child("AutoTemp").setValue("False");
            }
        });

        autoTemp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                autoTemp2.setChecked(isChecked);
                if (isChecked)
                    myRef.child("Aircond").child("Airc002").child("AutoTemp").setValue("True");
                else
                    myRef.child("Aircond").child("Airc002").child("AutoTemp").setValue("False");
            }
        });

        followTimetable1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                followTimetable1.setChecked(isChecked);
                if (isChecked)
                    myRef.child("Aircond").child("Airc001").child("FollowTimetable").setValue("True");
                else
                    myRef.child("Aircond").child("Airc001").child("FollowTimetable").setValue("False");
            }
        });

        followTimetable2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                followTimetable2.setChecked(isChecked);
                if (isChecked)
                    myRef.child("Aircond").child("Airc002").child("FollowTimetable").setValue("True");
                else
                    myRef.child("Aircond").child("Airc002").child("FollowTimetable").setValue("False");
            }
        });
    }

    public void increase1(View view) {
        txtTemp1 = findViewById(R.id.temp1);
        if (temp1 < 32 && status1 == true) {
            temp1 = temp1 + 1;
            myRef.child("Aircond").child("Airc001").child("Temp").setValue(temp1.toString());
        }
    }

    public void decrease1(View view) {
        txtTemp1 = findViewById(R.id.temp1);
        if (temp1 > 16 && status1 == true) {
            temp1 = temp1 - 1;
            myRef.child("Aircond").child("Airc001").child("Temp").setValue(temp1.toString());
        }
    }

    public void increase2(View view) {
        txtTemp2 = findViewById(R.id.temp2);
        if (temp2 < 32 && status2 == true) {
            temp2 = temp2 + 1;
            myRef.child("Aircond").child("Airc002").child("Temp").setValue(temp2.toString());
        }
    }

    public void decrease2(View view) {
        txtTemp2 = findViewById(R.id.temp2);
        if (temp2 > 16 && status2 == true) {
            temp2 = temp2 - 1;
            myRef.child("Aircond").child("Airc002").child("Temp").setValue(temp2.toString());
        }
    }
}
