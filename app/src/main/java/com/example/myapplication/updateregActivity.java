package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class updateregActivity extends AppCompatActivity {
    private RecyclerView reg_list;
    public DatabaseReference databaseReference;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatereg);
        reg_list = (RecyclerView) findViewById(R.id.reg_list);
        reg_list.setHasFixedSize(true);
        reg_list.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.keepSynced(true);


    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<user> options=
                new FirebaseRecyclerOptions.Builder<user>()
                        .setQuery(databaseReference,user.class)
                        .setLifecycleOwner(this)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<user, userViewHolder>(options) {
            @Override
            protected void onBindViewHolder(userViewHolder holder, final int position, final user model) {
                holder.setMobilenumber(model.getMobilenumber());
                holder.setAddress(model.getAddress());
                holder.setCity(model.getCity());
                holder.setFullname(model.getFullname());
                holder.setState(model.getState());
                holder.setPincode(model.getPincode());
                holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);
                        popupMenu.inflate(R.menu.opion_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.item_edit:
                                        final Intent save = new Intent(getApplicationContext(),saveActivity.class);
                                        save.putExtra("mobilenumber",model.getMobilenumber());
                                        save.putExtra("address",model.getAddress());
                                        save.putExtra("city",model.getCity());
                                        save.putExtra("state",model.getState());
                                        save.putExtra("age",model.getAge());
                                        save.putExtra("pincode",model.getPincode());
                                        save.putExtra("fullname",model.getFullname());
                                        save.putExtra("description",model.getDescription());
                                        save.putExtra("gender",model.getGender());
                                        save.putExtra("id",model.getId());
                                        String uniqueid= model.getId();
                                        Log.i("uniqueid",uniqueid);
                                        startActivity(save);
                                        break;
                                    case R.id.item_remove:
                                        final Intent remove = new Intent(getApplicationContext(),updateregActivity.class);
                                        //remove.putExtra("id" , model.getId());
                                        String uniqueid2= model.getId();
                                        databaseReference =FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(uniqueid2);
                                        databaseReference.removeValue();
                                        startActivity(remove);


                                        break;
                                     default:
                                         break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });
            }

            @NonNull
            @Override
            public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new userViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_row, parent, false));
            }
        };

        reg_list.setAdapter(firebaseRecyclerAdapter);


    }
    public static class userViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView txtOptionDigit;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            txtOptionDigit= (TextView) view.findViewById(R.id.txtOptionDigit);


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
