package com.example.smartac;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditScheduleActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference aircondTimetableRef;

    private TextView targetDay;
    private TextView startTime;
    private TextView endTime;
    private TextView changeStartTime;
    private TextView changeEndTime;

    boolean timeChecker; // false = endTime, true = startTime

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editschedule);
        Toolbar toolbar = findViewById(R.id.toolbar_controller);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        targetDay = findViewById(R.id.targetDay);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        changeStartTime = findViewById(R.id.changeSt);
        changeEndTime = findViewById(R.id.changeEd);

        String day = getIntent().getStringExtra("Day");
        String aircond = getIntent().getStringExtra("Aircond");
        targetDay.setText(aircond + "   " + day);

        aircondTimetableRef = myRef.child("Timetable").child(day).child(aircond).getRef();

        aircondTimetableRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                startTime.setText(dataSnapshot.child("StartTime").getValue(String.class));
                endTime.setText(dataSnapshot.child("EndTime").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changeStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeChecker = true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        changeEndTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                timeChecker = false;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String selectedTime = String.format("%02d:%02d:00", i, i1);

        if (timeChecker){
            startTime.setText(selectedTime);
            aircondTimetableRef.child("StartTime").setValue(selectedTime);
        } else {
            endTime.setText(selectedTime);
            aircondTimetableRef.child("EndTime").setValue(selectedTime);
        }

        Toast.makeText(getApplicationContext(), "Time changed", Toast.LENGTH_SHORT).show();
    }
}
