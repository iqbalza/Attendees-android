package com.example.iqbalzauqul.attendees.Activities.SignupOrLogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iqbalzauqul.attendees.Activities.MainActivities.MainActivity;
import com.example.iqbalzauqul.attendees.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by iqbalzauqul on 20/11/17.
 */

public class LoginActivity extends AppCompatActivity{


    ProgressDialog progressDialog;
    //Deklarasi widget
    private EditText emailField;
    private EditText passwordField;
    private Button signInBtn;
    private Button signUpBtn;
    private Button forgotButton;
    //Deklarasi autentikasi firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signInBtn = findViewById(R.id.email_sign_in_button);
        signUpBtn = findViewById(R.id.signUpButtonLogin);
        forgotButton = findViewById(R.id.lupaPasswordButton);

        progressDialog = new ProgressDialog(this);



        if (getIntent().hasExtra("logout")) {

            Toast.makeText(LoginActivity.this, "Anda telah logout.", Toast.LENGTH_SHORT).show();


        }
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if (getIntent().hasExtra("signup")) {
                        intent.putExtra("signup", true);
                    }
                    startActivity(intent);

                }
            }
        };

        //Method Sign-In
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();


            }
        });
        //Method sign-up
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent2);

            }
        });

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });


    }

    private void startSignIn() {

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this, "Harap isi email dan password", Toast.LENGTH_SHORT).show();


        } else {
            progressDialog.setMessage("Singing in..");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    String error = e.getMessage();
                    Toast.makeText(LoginActivity.this, "Sign gagal : " + error, Toast.LENGTH_LONG).show();


                }
            });

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthStateListener);
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

