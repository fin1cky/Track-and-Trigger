package com.example.trackntriggery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class dash extends AppCompatActivity {
    Button logout;
    TextView name;
    RadioGroup rg;
    Button ok;
    TextView prof;
    RadioButton r1;
    RadioButton r2;
    String pro_fire="";
    RadioButton rb;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        logout=findViewById(R.id.logout);
        name =findViewById(R.id.name);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                //LoginManager.getInstance().logOut();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        GoogleSignInAccount signInAccount=GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            name.setText("Signed in as: "+signInAccount.getDisplayName());
        }


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn){
            name.setText("Signed in as:"+ accessToken.getUserId());
        }

        rg=findViewById(R.id.radioGroup);
        ok=findViewById(R.id.ok);
        prof=findViewById(R.id.prof);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);

        /*if (r1.isActivated()){
            prof.setText("Selected Buisiness");
            pro_fire="Buisiness";
        }
        if (r2.isEnabled()){
            prof.setText("Selected Other");
            pro_fire="Other";
        }*/
        /*if (rg.getCheckedRadioButtonId()==-1){
            Toast.makeText(this, "Please select a option", Toast.LENGTH_SHORT).show();
        }
        else{
            int selectedId = rg.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
            Toast.makeText(getApplicationContext(), selectedRadioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
            prof.setText(selectedRadioButton.getText().toString()+"is selected");
            pro_fire=selectedRadioButton.getText().toString();
        }*/
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && (int)checkedId > -1) {
                    Toast.makeText(dash.this, rb.getText(), Toast.LENGTH_SHORT).show();

                }

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(dash.this, "Please select a option", Toast.LENGTH_SHORT).show();
                }
                else {
                    //int selectedId = rg.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    //RadioButton selectedRadioButton = (RadioButton)rg.findViewById(rg.getCheckedRadioButtonId());
                    //FirebaseDatabase.getInstance().getReference().child("Accounts").push().child("Profession").setValue(pro_fire);
                    //FirebaseUser user=mAuth.getCurrentUser();

                    //databaseReference.child("Users").child(user.getUid()).child("phone_num").setValue();
                    FirebaseUser u=FirebaseAuth.getInstance().getCurrentUser();
                    databaseReference.child("Users").child(u.getUid()).child("email").setValue(u.getEmail());
                    databaseReference.child("Users").child(u.getUid()).child("profession").setValue(rb.getText().toString());
                    if(rb.getText().toString().equals("Business")){
                        Intent intent=new Intent(getApplicationContext(),Divisions.class);
                        startActivity(intent);
                    }
                    else{
                        startActivity(new Intent(getApplicationContext(),OtherPage1.class));
                    }
                }
            }
        });
    }


}