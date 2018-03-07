package com.example.ragheed.liftlog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button startRegBtn, startLoginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //Widget instantiation
        startLoginBtn = findViewById(R.id.start_login_button);
        startRegBtn = findViewById(R.id.start_reg_button);
        //Firebase
        mAuth = FirebaseAuth.getInstance();

        startLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class );
                startActivity(intentLogin);

            }
        });
        startRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(MainActivity.this, RegisterActivity.class );
                startActivity(intentReg);
            }
        });



    }


}
