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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ragheed on 3/6/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button registerBtn;
    private Toolbar mToolbar;

    private TextInputLayout emailInput, passwordInput, passwordConfirmInput;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        //instantiation of widgets
        registerBtn = findViewById(R.id.reg_create_btn);
        emailInput = findViewById(R.id.reg_email);
        passwordInput = findViewById(R.id.reg_password);
        passwordConfirmInput = findViewById(R.id.reg_password_confirm);

        mToolbar = findViewById(R.id.reg_appbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();
                String passwordConfirm = passwordConfirmInput.getEditText().getText().toString();

                if (!email.isEmpty() && !password.isEmpty() && !passwordConfirm.isEmpty()){

                    if (password.equals(passwordConfirm)){

                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    Intent homePageIntent = new Intent(RegisterActivity.this, HomeActivity.class);
                                    startActivity(homePageIntent);
                                    finish();
                                }

                            }
                        });

                    }else {

                        Toast.makeText(RegisterActivity.this, "Make sure you entered the correct credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void sendToStart(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
