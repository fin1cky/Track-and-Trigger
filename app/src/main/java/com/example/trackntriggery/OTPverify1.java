package com.example.trackntriggery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OTPverify1 extends AppCompatActivity {

    String verificationCodebySystem, phone;
    Button verify,sotp;
    FirebaseAuth mAuth;
    EditText ophone;
    EditText number;
    String otpCode;
    PhoneAuthCredential credential;
    Boolean verificationOnProgress = false;
    Boolean b=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_pverify1);
        sotp = findViewById(R.id.SendOTP);
        verify = findViewById(R.id.verify);
        mAuth = FirebaseAuth.getInstance();
        ophone = findViewById(R.id.MOTP);
        verify.setVisibility(View.INVISIBLE);
        number = findViewById(R.id.MobileNo);

        //phone="+91"+number.getText().toString();


            sotp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!number.getText().toString().isEmpty() && number.getText().toString().length() == 10) {
                        if(!verificationOnProgress){
                            sotp.setEnabled(false);
                            //progressBar.setVisibility(View.VISIBLE);
                            String phoneNum = "+91"+number.getText().toString();
                            Log.d("phone", "Phone No.: " + phoneNum);
                            requestPhoneAuth(phoneNum);
                        }else {
                            sotp.setEnabled(true);

                            //otpEnter.setVisibility(View.VISIBLE);
                            //progressBar.setVisibility(View.VISIBLE);
                            otpCode = ophone.getText().toString();
                            if(otpCode.isEmpty()){
                                ophone.setError("Required");
                                return;
                            }

                            credential = PhoneAuthProvider.getCredential(verificationCodebySystem,otpCode);
                            verifyAuth(credential);
                        }

                    }else {
                        number.setError("Valid Phone Required");
                    }
                    sotp.setVisibility(View.INVISIBLE);
                    verify.setVisibility(View.VISIBLE);
                }

            });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    startActivity(new Intent(getApplicationContext(),dash.class));
                }
                startActivity(new Intent(getApplicationContext(),dash.class));
            }
        });
    }
    private void requestPhoneAuth(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,120L, TimeUnit.SECONDS,this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String s) {
                        super.onCodeAutoRetrievalTimeOut(s);
                        //Toast.makeText(register.this, "OTP Timeout, Please Re-generate the OTP Again.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCodebySystem = s;
                        //token = forceResendingToken;
                        verificationOnProgress = true;
                        //progressBar.setVisibility(View.INVISIBLE);
                        //mNext.setText("Verify");
                        //mNext.setEnabled(true);
                        //otpEnter.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                        // called if otp is automatically detected by the app
                        verifyAuth(phoneAuthCredential);

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        //Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

        private void verifyAuth(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential).addOnCompleteListener(OTPverify1.this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(OTPverify1.this, "Verification complete", Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    databaseReference.child("Users").child(user.getUid()).child("phone number").setValue(phone);
                    databaseReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild("profession")){
                                Intent intent=new Intent(getApplicationContext(),Divisions.class);
                                startActivity(intent);
                            }
                            else{
                                b=true;
                                Intent intent=new Intent(getApplicationContext(),dash.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //startActivity(new Intent(getApplicationContext(), dash.class));
                }
                else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText( OTPverify1.this, "Verification incomplete", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    /*private void requestOTP() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodebySystem = s;
            sotp.setVisibility(View.GONE);
            verify.setVisibility(View.VISIBLE);
        }
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = ophone.getText().toString();
            if (code != null) {
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPverify1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodebySystem, code);
        signInTheUserwithCredentials(credential);
    }
    */

}

