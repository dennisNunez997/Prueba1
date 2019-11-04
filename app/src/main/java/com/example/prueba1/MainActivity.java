package com.example.prueba1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText EditTextName, EditTextEmail, EditTextPassword;
    private Button btnRegistro;
    //variables de los datos para registrar
    private String name="";
    private String email="";
    private String password="";

    FirebaseAuth Auth;
    DatabaseReference Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference();

        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextEmail = (EditText) findViewById(R.id.EditTextEmail);
        EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);

        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                name = EditTextName.getText().toString();
                email = EditTextEmail.getText().toString();
                password = EditTextPassword.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty())
                {
                    if(password.length()>=6)
                    {
                        registrarUsuario();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "el password debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "debe completar los campos",Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
    private void registrarUsuario()
    {
        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("pass", password);



                    String id = Auth.getCurrentUser().getUid();
                    Database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful())
                            {
                                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "No se pudo crear los datos correctamente",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No se pudo registrar este usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
