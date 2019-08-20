package com.example.smartac;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

public class ScheduleActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference aircondRef = myRef.child("Aircond").getRef();

    private Spinner sprDay;
    private String selectedAircond;
    private String[] airconds;

    private ProgressBar progressBar;
    private TextView monSt;
    private TextView monEd;
    private TextView tuesSt;
    private TextView tuesEd;
    private TextView wedSt;
    private TextView wedEd;
    private TextView thursSt;
    private TextView thursEd;
    private TextView friSt;
    private TextView friEd;
    private TextView satSt;
    private TextView satEd;
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

        monSt = findViewById(R.id.monStart);
        monEd = findViewById(R.id.monEnd);
        tuesSt = findViewById(R.id.tuesStart);
        tuesEd = findViewById(R.id.tuesEnd);
        wedSt = findViewById(R.id.wedStart);
        wedEd = findViewById(R.id.wedEnd);
        thursSt = findViewById(R.id.thursStart);
        thursEd = findViewById(R.id.thursEnd);
        friSt = findViewById(R.id.friStart);
        friEd = findViewById(R.id.friEnd);
        satSt = findViewById(R.id.satStart);
        satEd = findViewById(R.id.satEnd);
        sunSt = findViewById(R.id.sunStart);
        sunEd = findViewById(R.id.sunEnd);
        editMon = findViewById(R.id.editMon);
        editTues = findViewById(R.id.editTues);
        editWed = findViewById(R.id.editWed);
        editThurs = findViewById(R.id.editThurs);
        editFri = findViewById(R.id.editFri);
        editSat = findViewById(R.id.editSat);
        editSun = findViewById(R.id.editSun);
        sprDay = findViewById(R.id.spr_day);

        aircondRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                airconds = new String[(int) dataSnapshot.getChildrenCount()];
                int pointer = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    airconds[pointer] = ds.getKey();
                    pointer++;
                }

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(ScheduleActivity.this, android.R.layout.simple_spinner_item, airconds);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sprDay.setAdapter(spinnerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sprDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAircond = adapterView.getItemAtPosition(i).toString();

                Toast.makeText(getApplicationContext(), "Showing " + selectedAircond + " timetable...",Toast.LENGTH_SHORT).show();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        monSt.setText(dataSnapshot.child("Timetable").child("Monday").child(selectedAircond).child("StartTime").getValue(String.class));
                        tuesSt.setText(dataSnapshot.child("Timetable").child("Tuesday").child(selectedAircond).child("StartTime").getValue(String.class));
                        wedSt.setText(dataSnapshot.child("Timetable").child("Wednesday").child(selectedAircond).child("StartTime").getValue(String.class));
                        thursSt.setText(dataSnapshot.child("Timetable").child("Thursday").child(selectedAircond).child("StartTime").getValue(String.class));
                        friSt.setText(dataSnapshot.child("Timetable").child("Friday").child(selectedAircond).child("StartTime").getValue(String.class));
                        satSt.setText(dataSnapshot.child("Timetable").child("Saturday").child(selectedAircond).child("StartTime").getValue(String.class));
                        sunSt.setText(dataSnapshot.child("Timetable").child("Sunday").child(selectedAircond).child("StartTime").getValue(String.class));

                        monEd.setText(dataSnapshot.child("Timetable").child("Monday").child(selectedAircond).child("EndTime").getValue(String.class));
                        tuesEd.setText(dataSnapshot.child("Timetable").child("Tuesday").child(selectedAircond).child("EndTime").getValue(String.class));
                        wedEd.setText(dataSnapshot.child("Timetable").child("Wednesday").child(selectedAircond).child("EndTime").getValue(String.class));
                        thursEd.setText(dataSnapshot.child("Timetable").child("Thursday").child(selectedAircond).child("EndTime").getValue(String.class));
                        friEd.setText(dataSnapshot.child("Timetable").child("Friday").child(selectedAircond).child("EndTime").getValue(String.class));
                        satEd.setText(dataSnapshot.child("Timetable").child("Saturday").child(selectedAircond).child("EndTime").getValue(String.class));
                        sunEd.setText(dataSnapshot.child("Timetable").child("Sunday").child(selectedAircond).child("EndTime").getValue(String.class));

                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void editMon_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Monday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

    public void editTues_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Tuesday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

    public void editWed_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Wednesday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

    public void editThurs_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Thursday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

    public void editFri_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Friday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

    public void editSat_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Saturday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

    public void editSun_OnClick(View view) {
        Intent intent = new Intent(getBaseContext(), EditScheduleActivity.class);
        intent.putExtra("Day", "Sunday");
        intent.putExtra("Aircond", selectedAircond);
        startActivity(intent);
    }

}
