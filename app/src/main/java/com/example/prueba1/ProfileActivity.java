package com.example.prueba1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private Button ButtonSignOut;
    private Button btn14,btn15,btn24,btn26,btn32,btn39,btn48,btn51,btnCamara;
    private TextView textViewName;
    private FirebaseAuth Auth;
    private DatabaseReference Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference();
        textViewName = (TextView) findViewById(R.id.TextViewName);
        btn14 = (Button) findViewById(R.id.btn14);
        btn15 = (Button) findViewById(R.id.btn15);
        btn24 = (Button) findViewById(R.id.btn24);
        btn26 = (Button) findViewById(R.id.btn26);
        btn32 = (Button) findViewById(R.id.btn32);
        btn39 = (Button) findViewById(R.id.btn39);
        btn48 = (Button) findViewById(R.id.btn48);
        btn51 = (Button) findViewById(R.id.btn51);
        btnCamara = (Button) findViewById(R.id.btnCamara);

        ButtonSignOut = (Button) findViewById(R.id.btnSignout);
        ButtonSignOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Auth.signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        btn14.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio14.class));
            }
        });

        btn15.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio15.class));
            }
        });

        btn24.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio24.class));
            }
        });

        btn26.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio26.class));
            }
        });

        btn32.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio32.class));
            }
        });

        btn39.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio39.class));
            }
        });

        btn48.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio48.class));
            }
        });

        btn51.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, ejercicio51.class));
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, CamaraActivity.class));
            }
        });


    }

    private void getUserInfo()
    {
        String id = Auth.getCurrentUser().getUid();
        Database.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String name = dataSnapshot.child("name").getValue().toString();
                    textViewName.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
