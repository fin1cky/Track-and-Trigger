package com.example.trackntriggery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN=123;
    private FirebaseAuth mAuth;
    FirebaseAuth fauth;
    EditText oemail,opassword;
    Button gbutton,login;
    private LoginButton loginButton;
    //CallbackManager callbackManager;
    CallbackManager mCallbackManager;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            /*Intent intent=new Intent(getApplicationContext(),dash.class);
            startActivity(intent);*/
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("Users").child(user.getUid()).child("profession").equals("Business")){
                        startActivity(new Intent(getApplicationContext(),Divisions.class));
                    }
                    else if(snapshot.child("Users").child(user.getUid()).child("profession").equals("Other")){
                        startActivity(new Intent(getApplicationContext(),OtherPage1.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            startActivity(new Intent(getApplicationContext(),dash.class));
        }
        /*if (FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("profession").equals("Business")){
            startActivity(new Intent(getApplicationContext(),Divisions.class));
        }
        */

    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oemail = findViewById(R.id.email);
        opassword = findViewById(R.id.upassword);
        fauth = FirebaseAuth.getInstance();
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register2.class));
            }
        });
         findViewById(R.id.Login_button).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String memail = oemail.getText().toString().trim();
                 String password = opassword.getText().toString().trim();
                 if(TextUtils.isEmpty(memail))
                 {
                     oemail.setError("Email is required");
                 }
                 if(TextUtils.isEmpty(password))
                 {
                     opassword.setError("Password is required");
                 }

                 if(password.length() < 6)
                 {
                     opassword.setError("Password at least 6 characters long");
                 }
                 fauth.signInWithEmailAndPassword(memail,password).addOnCompleteListener(task -> {
                     if(task.isSuccessful())
                     {
                         if(fauth.getCurrentUser().isEmailVerified()){
                             Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getApplicationContext(),dash.class));
                         }
                          else Toast.makeText(MainActivity.this, "Verify email ", Toast.LENGTH_SHORT).show();
                     }
                     else
                         Toast.makeText(MainActivity.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                 });

             }
         });
        mAuth=FirebaseAuth.getInstance();
        createRequest();
        findViewById(R.id.gbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                //Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
// ...


        /*loginButton=findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //TextView name=findViewById(R.id.name);
                //name.setText("Signed-in as:"+loginResult.getAccessToken().getUserId());
                Intent intent=new Intent(getApplicationContext(),dash.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                //Log.w(TAG, "Google sign in failed", e);
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            databaseReference.child("Users").child(user.getUid()).child("username").setValue(user.getDisplayName());
                            databaseReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(!snapshot.hasChild("phone number")){
                                        Intent intent=new Intent(getApplicationContext(),OTPverify1.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        databaseReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.hasChild("profession")) {
                                                    Intent intent = new Intent(getApplicationContext(), Divisions.class);
                                                    startActivity(intent);
                                                } else {
                                                    Intent intent = new Intent(getApplicationContext(), dash.class);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                //databaseReference.child("Users").child(user.getUid()).child("username").setValue(user.getDisplayName());
                                    /*u1=new User(user.getDisplayName(),user.getEmail(),user.getUid());
                                    Intent intent=new Intent(getApplicationContext(),dash.class);
                                    startActivity(intent);*/
                                            }
                                            //updateUI(user);

                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                            Toast.makeText(MainActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    private void handleFacebookAccessToken(AccessToken token) {
        //Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            databaseReference.child("Users").child(user.getUid()).child("username").setValue(user.getDisplayName());
                            databaseReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChild("profession")) {
                                        Intent intent = new Intent(getApplicationContext(), Divisions.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), dash.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    //databaseReference.child("Users").child(user.getUid()).child("username").setValue(user.getDisplayName());
                                    /*u1=new User(user.getDisplayName(),user.getEmail(),user.getUid());
                                    Intent intent=new Intent(getApplicationContext(),dash.class);
                                    startActivity(intent);*/
                                }
                                //updateUI(user);
                            });
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}