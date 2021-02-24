package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class confirmAppoint extends AppCompatActivity {
    Button confirmAppointment;
    DatabaseReference mreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appoint);
        confirmAppointment = (Button) findViewById(R.id.confirmAppointment);
        mreference = FirebaseDatabase.getInstance().getReference("Appointment").child("Pending Appointment");
        confirmAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String docselectedid=getIntent().getStringExtra("dockey");
                String appointid= mreference.push().getKey();
                DatabaseReference appoint = mreference.child(docselectedid).child(appointid);
                appoint.child("fullname").setValue(getIntent().getStringExtra("fullname"));
                appoint.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                appoint.child("appointkey").setValue(appointid);
                appoint.child("dockey").setValue(getIntent().getStringExtra("dockey"));
                appoint.child("docName").setValue(getIntent().getStringExtra("docName"));
                appoint.child("docProf").setValue(getIntent().getStringExtra("docProf"));
                appoint.child("descriptionDoc").setValue(getIntent().getStringExtra("descriptionDoc"));
                appoint.child("mobilenumber").setValue(getIntent().getStringExtra("mobilenumber"));
                appoint.child("address").setValue(getIntent().getStringExtra("address"));
                appoint.child("city").setValue(getIntent().getStringExtra("city"));
                appoint.child("state").setValue(getIntent().getStringExtra("state"));
                appoint.child("age").setValue(getIntent().getStringExtra("age"));
                appoint.child("pincode").setValue(getIntent().getStringExtra("pincode"));
                appoint.child("description").setValue(getIntent().getStringExtra("description"));
                appoint.child("gender").setValue(getIntent().getStringExtra("gender"));
                appoint.child("usseregrid").setValue(getIntent().getStringExtra("id"));
                Toast.makeText(confirmAppoint.this, "Your Appointment is suceesfully booked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
