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

public class LoginActivity extends AppCompatActivity {
    private EditText EditTextEmail;
    private EditText EditTextPassword;
    private Button ButtonLogin;

    private String email ="";
    private String password="";

    private FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Auth = FirebaseAuth.getInstance();

        EditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        ButtonLogin = (Button) findViewById(R.id.btnLogin);

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = EditTextEmail.getText().toString();
                password = EditTextPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty())
                {
                    loginUser();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void loginUser()
    {
        Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                    finish();
               }
               else
               {
                   Toast.makeText(LoginActivity.this, "No se puede iniciar sesion y que compruebe los datos", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}
