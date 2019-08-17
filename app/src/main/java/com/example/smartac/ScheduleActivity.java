package com.example.smartac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScheduleActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private ProgressBar progressBar;
    private TextView monAC;
    private TextView monSt;
    private TextView monEd;
    private TextView tuesAC;
    private TextView tuesSt;
    private TextView tuesEd;
    private TextView wedAC;
    private TextView wedSt;
    private TextView wedEd;
    private TextView thursAC;
    private TextView thursSt;
    private TextView thursEd;
    private TextView friAC;
    private TextView friSt;
    private TextView friEd;
    private TextView satAC;
    private TextView satSt;
    private TextView satEd;
    private TextView sunAC;
    private TextView sunSt;
    private TextView sunEd;
    private Button editMon;
    private Button editTues;
    private Button editWed;
    private Button editThurs;
    private Button editFri;
    private Button editSat;
    private Button editSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = findViewById(R.id.toolbar_controller);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);

        monAC = findViewById(R.id.monAC);
        monSt = findViewById(R.id.monStart);
        monEd = findViewById(R.id.monEnd);
        tuesAC = findViewById(R.id.tuesAC);
        tuesSt = findViewById(R.id.tuesStart);
        tuesEd = findViewById(R.id.tuesEnd);
        wedAC = findViewById(R.id.wedAC);
        wedSt = findViewById(R.id.wedStart);
        wedEd = findViewById(R.id.wedEnd);
        thursAC = findViewById(R.id.thursAC);
        thursSt = findViewById(R.id.thursStart);
        thursEd = findViewById(R.id.thursEnd);
        friAC = findViewById(R.id.friAC);
        friSt = findViewById(R.id.friStart);
        friEd = findViewById(R.id.friEnd);
        satAC = findViewById(R.id.satAC);
        satSt = findViewById(R.id.satStart);
        satEd = findViewById(R.id.satEnd);
        sunAC = findViewById(R.id.sunAC);
        sunSt = findViewById(R.id.sunStart);
        sunEd = findViewById(R.id.sunEnd);
        editMon = findViewById(R.id.editMon);
        editTues = findViewById(R.id.editTues);
        editWed = findViewById(R.id.editWed);
        editThurs = findViewById(R.id.editThurs);
        editFri = findViewById(R.id.editFri);
        editSat = findViewById(R.id.editSat);
        editSun = findViewById(R.id.editSun);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                monAC.setText(dataSnapshot.child("Timetable").child("Monday").child("Mon001").child("Aircond").getValue(String.class));
                tuesAC.setText(dataSnapshot.child("Timetable").child("Tuesday").child("Tue001").child("Aircond").getValue(String.class));
                wedAC.setText(dataSnapshot.child("Timetable").child("Wednesday").child("Wed001").child("Aircond").getValue(String.class));
                thursAC.setText(dataSnapshot.child("Timetable").child("Thursday").child("Thu001").child("Aircond").getValue(String.class));
                friAC.setText(dataSnapshot.child("Timetable").child("Friday").child("Fri001").child("Aircond").getValue(String.class));
                satAC.setText(dataSnapshot.child("Timetable").child("Saturday").child("Sat001").child("Aircond").getValue(String.class));
                sunAC.setText(dataSnapshot.child("Timetable").child("Sunday").child("Sun001").child("Aircond").getValue(String.class));

                monSt.setText(dataSnapshot.child("Timetable").child("Monday").child("Mon001").child("StartTime").getValue(String.class));
                tuesSt.setText(dataSnapshot.child("Timetable").child("Tuesday").child("Tue001").child("StartTime").getValue(String.class));
                wedSt.setText(dataSnapshot.child("Timetable").child("Wednesday").child("Wed001").child("StartTime").getValue(String.class));
                thursSt.setText(dataSnapshot.child("Timetable").child("Thursday").child("Thu001").child("StartTime").getValue(String.class));
                friSt.setText(dataSnapshot.child("Timetable").child("Friday").child("Fri001").child("StartTime").getValue(String.class));
                satSt.setText(dataSnapshot.child("Timetable").child("Saturday").child("Sat001").child("StartTime").getValue(String.class));
                sunSt.setText(dataSnapshot.child("Timetable").child("Sunday").child("Sun001").child("StartTime").getValue(String.class));

                monSt.setText(dataSnapshot.child("Timetable").child("Monday").child("Mon001").child("EndTime").getValue(String.class));
                tuesSt.setText(dataSnapshot.child("Timetable").child("Tuesday").child("Tue001").child("EndTime").getValue(String.class));
                wedSt.setText(dataSnapshot.child("Timetable").child("Wednesday").child("Wed001").child("EndTime").getValue(String.class));
                thursSt.setText(dataSnapshot.child("Timetable").child("Thursday").child("Thu001").child("EndTime").getValue(String.class));
                friSt.setText(dataSnapshot.child("Timetable").child("Friday").child("Fri001").child("EndTime").getValue(String.class));
                satSt.setText(dataSnapshot.child("Timetable").child("Saturday").child("Sat001").child("EndTime").getValue(String.class));
                sunSt.setText(dataSnapshot.child("Timetable").child("Sunday").child("Sun001").child("EndTime").getValue(String.class));

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void editMon_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Monday");
        startActivity(intent);
    }

    public void editTues_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Tuesday");
        startActivity(intent);
    }

    public void editWed_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Wednesday");
        startActivity(intent);
    }

    public void editThurs_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Thursday");
        startActivity(intent);
    }

    public void editFri_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Friday");
        startActivity(intent);
    }

    public void editSat_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Saturday");
        startActivity(intent);
    }

    public void editSun_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Target", "Sunday");
        startActivity(intent);
    }

}
