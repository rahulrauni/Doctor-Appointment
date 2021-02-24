package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {
    EditText mobileText, pincodeText, ageText, cityText,stateText, addressText,nameField, descriptionEditText;
    Button uploadbutton;
    RadioButton maleButton, femaleButton;
    DatabaseReference docDatabase;
    String gender = "";
    String mkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mobileText = findViewById(R.id.mobileText);
        pincodeText = findViewById(R.id.pincodeText);
        ageText = findViewById(R.id.ageText);
        cityText = findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        addressText = findViewById(R.id.addressText);
        nameField = findViewById(R.id.nameField);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        uploadbutton = findViewById(R.id.uploadbutton);
        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);
        docDatabase = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startadding();


            }
        });

    }
    private void startadding(){
        final String mobilenumber = mobileText.getText().toString();
        final String city = cityText.getText().toString();
        final String state = stateText.getText().toString();
        final String age =  ageText.getText().toString();
        final String pincode = pincodeText.getText().toString();
        final String fullName = nameField.getText().toString();
        final String address = addressText.getText().toString();
        final String description= descriptionEditText.getText().toString();
        if(maleButton.isChecked()){
            gender = "Male";
        }
        if(femaleButton.isChecked()){
            gender = "Female";
        }
        //DatabaseReference newuser = databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push();
        mkey = docDatabase.push().getKey();
        user user1=new user(mobilenumber,address,pincode,age,fullName,gender,state,city,description,mkey);
        Toast.makeText(getApplicationContext(), mkey, Toast.LENGTH_SHORT).show();
//        newuser.child("mobilenumber").setValue(mobilenumber);
//        newuser.child("city").setValue(city);
//        newuser.child("state").setValue(state);
//        newuser.child("age").setValue(age);
//        newuser.child("pincode").setValue(pincode);
//        newuser.child("fullname").setValue(fullName);
//        newuser.child("address").setValue(address);
//        newuser.child("description").setValue(description);
//        newuser.child("gender").setValue(gender);
//        newuser.child("uniquekey").setValue(databaseReference.push().getKey());
        docDatabase.child(mkey).setValue(user1);
        startActivity(new Intent(getApplicationContext(),MainActivity.class));


    }
}
