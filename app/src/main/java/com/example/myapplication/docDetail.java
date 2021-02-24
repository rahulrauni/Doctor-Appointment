package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class docDetail extends AppCompatActivity {
    private RecyclerView docreg_list;
    public DatabaseReference docdeataildatabase;
    FirebaseRecyclerAdapter docrecycleradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_detail);
        docreg_list = (RecyclerView) findViewById(R.id.docreg_list);
        docreg_list.setHasFixedSize(true);
        docreg_list.setLayoutManager(new LinearLayoutManager(this));
        docdeataildatabase = FirebaseDatabase.getInstance().getReference("Doctor");
        docdeataildatabase.keepSynced(true);
    }
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<doctor> option=
                new FirebaseRecyclerOptions.Builder<doctor>()
                        .setQuery(docdeataildatabase,doctor.class)
                        .setLifecycleOwner(this)
                        .build();

        docrecycleradapter = new FirebaseRecyclerAdapter<doctor , doctorViewHolder>(option) {

            @NonNull
            @Override
            public doctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new doctorViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.doc_row, parent, false));
            }

            @Override
            protected void onBindViewHolder(doctorViewHolder holder,final int position, final doctor model) {
                holder.setDocName(model.getDocName());
                holder.setDocProf(model.getDocProf());
                holder.setDescriptionDoc(model.getDescriptionDoc());
                holder.setAvailability(model.getAvailability());
                String docavailability = model.getAvailability();

                switch (docavailability){
                    case "Available":
                        holder.chooseDoctorButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //startActivity(new Intent(docDetail.this,updateregActivity.class));
                                final Intent userintent = new Intent(getApplicationContext(),userRegDetail.class);
                                userintent.putExtra("docName",model.getDocName());
                                userintent.putExtra("docProf",model.getDocProf());
                                userintent.putExtra("descriptionDoc",model.getDescriptionDoc());
                                userintent.putExtra("dockey",model.getDockey());
                                startActivity(userintent);
                            }
                        });

                        break;
                    case "Not Available":
                        holder.chooseDoctorButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(docDetail.this, "this doctor is not available try another doctor", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    default:
                        break;
                }

            }
        };
        docreg_list.setAdapter(docrecycleradapter);

    }
    public static class doctorViewHolder extends RecyclerView.ViewHolder{
        View view;
        Button chooseDoctorButton;

        public doctorViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            chooseDoctorButton = (Button) view.findViewById(R.id.chooseDoctorButton);

        }
        public void setDocName(String docName){
            TextView docNameText = (TextView) view.findViewById(R.id.docNameText);
            docNameText.setText(docName);

        }
        public void setDocProf(String docProf){
            TextView docProfText = (TextView) view.findViewById(R.id.docProfText);
            docProfText.setText(docProf);

        }
        public void setDescriptionDoc(String descriptionDoc){
            TextView descriptionDocText = (TextView) view.findViewById(R.id.descriptionDocText);
            descriptionDocText.setText(descriptionDoc);

        }
        public void setAvailability(String availability){
            TextView availableText = view.findViewById(R.id.availableText);
            availableText.setText(availability);
        }
    }
}
