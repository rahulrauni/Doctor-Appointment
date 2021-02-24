package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class saveActivity extends AppCompatActivity {
    EditText mobileText, pincodeText, ageText, cityText,stateText, addressText,nameField, descriptionEditText;
    Button savebutton;
    RadioGroup genderGroup;
    RadioButton radioButtonFemale;
    RadioButton radioButtonMale;
    DatabaseReference databaseReference;

    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        mobileText = findViewById(R.id.mobileText);
        pincodeText = findViewById(R.id.pincodeText);
        ageText = findViewById(R.id.ageText);
        cityText = findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        addressText = findViewById(R.id.addressText);
        nameField = findViewById(R.id.nameField);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        savebutton = findViewById(R.id.savebutton);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        genderGroup = findViewById(R.id.genderGroup);

        mobileText.setText(getIntent().getStringExtra("mobilenumber"));
        pincodeText.setText(getIntent().getStringExtra("pincode"));
        ageText.setText(getIntent().getStringExtra("age"));
        cityText.setText(getIntent().getStringExtra("city"));
        stateText.setText(getIntent().getStringExtra("state"));
        addressText.setText(getIntent().getStringExtra("address"));
        nameField.setText(getIntent().getStringExtra("fullname"));
        descriptionEditText.setText(getIntent().getStringExtra("description"));
        String key = getIntent().getStringExtra("id");
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
        Log.i("unique",key);

        String sex = getIntent().getStringExtra("gender");
        switch (sex){
            case "Male":
                genderGroup.check(R.id.radioButtonMale);
                break;
            case "Female" :
                genderGroup.check(R.id.radioButtonFemale);
                break;
            default:
                break;
        }
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("mobilemumber").setValue(mobileText.getText().toString());
                        dataSnapshot.getRef().child("pincode").setValue(pincodeText.getText().toString());
                        dataSnapshot.getRef().child("city").setValue(cityText.getText().toString());
                        dataSnapshot.getRef().child("state").setValue(stateText.getText().toString());
                        dataSnapshot.getRef().child("fullname").setValue(nameField.getText().toString());
                        dataSnapshot.getRef().child("descriptjon").setValue(descriptionEditText.getText().toString());
                        dataSnapshot.getRef().child("age").setValue(ageText.getText().toString());
                        dataSnapshot.getRef().child("address").setValue(addressText.getText().toString());
                        if(radioButtonMale.isChecked()){
                            gender="Male";
                        }
                        else if(radioButtonFemale.isChecked()){
                            gender="Female";
                        }
                        saveActivity.this.finish();

                       dataSnapshot.getRef().child("gender").setValue(gender);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



    }
}
