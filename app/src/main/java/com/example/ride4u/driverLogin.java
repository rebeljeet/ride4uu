package com.example.ride4u;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class driverLogin extends AppCompatActivity
{
    private EditText email,password;
    private Button login,registration;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registration = findViewById(R.id.registration);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String demail = email.getText().toString();
                final String dpassword = password.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(demail,dpassword).addOnCompleteListener(driverLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(driverLogin.this, "Sign in error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String demail = email.getText().toString();
                final String dpassword = password.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(demail,dpassword).addOnCompleteListener(driverLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(driverLogin.this, "Sign up error", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                            current_user.setValue(true);
                        }
                    }
                });
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    Intent intent = new Intent(driverLogin.this,DriverMapActivity.class);
                    startActivity(intent);
                    return;
                }
            }
        };
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
