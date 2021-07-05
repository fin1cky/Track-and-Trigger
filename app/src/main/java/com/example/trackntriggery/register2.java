package com.example.trackntriggery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class register2 extends AppCompatActivity {
    EditText musername, memail, mpassword;
    Button Loginbtn;
    Button registerbtn, VerifyEmail;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        musername = findViewById(R.id.SignupUsername);
        memail = findViewById(R.id.Email);
        mpassword = findViewById(R.id.SignupPassword);
        VerifyEmail = findViewById(R.id.verifymail);
        fauth = FirebaseAuth.getInstance();
        registerbtn = findViewById(R.id.Registerbutton);
        Loginbtn = findViewById(R.id.login_register);
        registerbtn.setVisibility(View.INVISIBLE);
        Loginbtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
        VerifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    memail.setError("Email is needed");
                }
                String password = mpassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    mpassword.setError("At least 6 characters long");
                    return;
                }
                fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        fauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    VerifyEmail.setVisibility(View.GONE);
                                    registerbtn.setVisibility(View.VISIBLE);

                                    registerbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), OTPverify1.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            else {
                                    Toast.makeText(register2.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(register2.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}