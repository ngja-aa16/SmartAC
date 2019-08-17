package com.example.smartac;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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

import java.util.Objects;

public class ViewActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private TextView roomTemp1;
    private TextView roomTemp2;
    private TextView acStatus1;
    private TextView acStatus2;
    private TextView acTemp1;
    private TextView acTemp2;
    private ProgressBar prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Toolbar toolbar = findViewById(R.id.toolbar_controller);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        roomTemp1 = findViewById(R.id.roomTemp1);
        roomTemp2 = findViewById(R.id.roomTemp2);
        acStatus1 = findViewById(R.id.acStatus1);
        acStatus2 = findViewById(R.id.acStatus2);
        acTemp1 = findViewById(R.id.acTemp1);
        acTemp2 = findViewById(R.id.acTemp2);
        prog = findViewById(R.id.progressBar3);
        prog.setVisibility(View.VISIBLE);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomTemp1.setText(dataSnapshot.child("Venue").child("DKABA").child("Temp").getValue(String.class) + " °C");
                roomTemp2.setText(dataSnapshot.child("Venue").child("DKABB").child("Temp").getValue(String.class) + " °C");
                acStatus1.setText(dataSnapshot.child("Aircond").child("Airc001").child("Status").getValue(String.class));
                acStatus2.setText(dataSnapshot.child("Aircond").child("Airc002").child("Status").getValue(String.class));

                if (acStatus1.getText().equals("On")){
                    acStatus1.setTextColor(Color.GREEN);
                    acTemp1.setText(dataSnapshot.child("Aircond").child("Airc001").child("Temp").getValue(String.class) + " °C");
                }
                else{
                    acStatus1.setTextColor(Color.RED);
                    acTemp1.setText("N/A");
                }

                if (acStatus2.getText().equals("On")){
                    acStatus2.setTextColor(Color.GREEN);
                    acTemp2.setText(dataSnapshot.child("Aircond").child("Airc002").child("Temp").getValue(String.class) + " °C");
                }
                else{
                    acStatus2.setTextColor(Color.RED);
                    acTemp2.setText("N/A");
                }

                prog.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
