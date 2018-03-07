package com.example.ragheed.liftlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ragheed on 3/6/2018.
 */

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button loginBtn;
    private Toolbar mToolbar;
    private TextInputLayout email_input, password_input;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Firebase auth instance

        mAuth = FirebaseAuth.getInstance();

        //Widget instantiation
        loginBtn = findViewById(R.id.login_btn);
        email_input = findViewById(R.id.login_email);
        password_input = findViewById(R.id.login_password);

        mToolbar = findViewById(R.id.login_appbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_input.getEditText().getText().toString();
                String password = password_input.getEditText().getText().toString();
                if (!email.isEmpty() && !password.isEmpty()){
                    signInUser(email, password);
                }

            }
        });




    }
    private void signInUser(String email, String pass){
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent homePageIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homePageIntent);
                    finish();
                }
            }
        });

    }
}
