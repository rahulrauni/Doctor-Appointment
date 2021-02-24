package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userRegDetail extends AppCompatActivity {
    private RecyclerView choosereg_list;
    public DatabaseReference userDetailDatabase;
    FirebaseRecyclerAdapter userDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg_detail);
        choosereg_list = (RecyclerView) findViewById(R.id.choosereg_list);
        choosereg_list.setHasFixedSize(true);
        choosereg_list.setLayoutManager(new LinearLayoutManager(this));
        userDetailDatabase = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        userDetailDatabase.keepSynced(true);

        //datapassing
//        String dockey = getIntent().getStringExtra("dockey");
//        String docName = getIntent().getStringExtra("docName");
//        String docProf = getIntent().getStringExtra("docProf");
//        String descriptionDoc = getIntent().getStringExtra("descriptionDoc");
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<user> optioner=
                new FirebaseRecyclerOptions.Builder<user>()
                        .setQuery(userDetailDatabase,user.class)
                        .setLifecycleOwner(this)
                        .build();
        userDetailAdapter = new FirebaseRecyclerAdapter<user,userViewHolder>(optioner){

            @NonNull
            @Override
            public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new userViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.carduserdetail, parent, false));
            }

            @Override
            protected void onBindViewHolder(userViewHolder holder,final int position, final user model) {
                holder.setMobilenumber(model.getMobilenumber());
                holder.setAddress(model.getAddress());
                holder.setCity(model.getCity());
                holder.setFullname(model.getFullname());
                holder.setState(model.getState());
                holder.setPincode(model.getPincode());
                holder.chooseUserRegButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //startActivity(new Intent(getApplicationContext(),confirmAppoint.class));
                        final Intent confirmAppoint = new Intent(getApplicationContext(), confirmAppoint.class);
                        confirmAppoint.putExtra("mobilenumber",model.getMobilenumber());
                        confirmAppoint.putExtra("address",model.getAddress());
                        confirmAppoint.putExtra("city",model.getCity());
                        confirmAppoint.putExtra("state",model.getState());
                        confirmAppoint.putExtra("age",model.getAge());
                        confirmAppoint.putExtra("pincode",model.getPincode());
                        confirmAppoint.putExtra("fullname",model.getFullname());
                        confirmAppoint.putExtra("description",model.getDescription());
                        confirmAppoint.putExtra("gender",model.getGender());
                        confirmAppoint.putExtra("id",model.getId());
                        confirmAppoint.putExtra("dockey",getIntent().getStringExtra("dockey"));
                        confirmAppoint.putExtra("docName",getIntent().getStringExtra("docName"));
                        confirmAppoint.putExtra("docProf",getIntent().getStringExtra("docProf"));
                        confirmAppoint.putExtra("descriptionDoc",getIntent().getStringExtra("descriptionDoc"));
                        startActivity(confirmAppoint);
                    }
                });

            }
        };
        choosereg_list.setAdapter(userDetailAdapter);
    }
    public static class userViewHolder extends RecyclerView.ViewHolder{
        View view;
        Button chooseUserRegButton;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            chooseUserRegButton = view.findViewById(R.id.chooseUserRegButton);


        }

        public void setMobilenumber(String mobilenumber){
            TextView mobilereg = (TextView) view.findViewById(R.id.mobilereg);
            mobilereg.setText(mobilenumber);

        }
        public void setAddress(String address){
            TextView addreg = (TextView) view.findViewById(R.id.addreg);
            addreg.setMovementMethod(new ScrollingMovementMethod());
            addreg.setText(address);
        }
        public void setPincode(String pincode){
            TextView pincodereg = (TextView) view.findViewById(R.id.pincodereg);
            pincodereg.setText(pincode);
        }
        public void setState(String state){
            TextView statereg = (TextView) view.findViewById(R.id.statereg);
            statereg.setText(state);
        }
        public void setCity(String city){
            TextView cityreg = (TextView) view.findViewById(R.id.cityreg);
            cityreg.setText(city);
        }
        public void setFullname(String fullname){
            TextView namereg = (TextView) view.findViewById(R.id.namereg);
            namereg.setText(fullname);
        }


    }
}
