package com.example.trackntriggery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class diary_full_view extends AppCompatActivity {
    Button close,save;
    TextView title,date;
    EditText msg;
    //Context context;
    String tit,dat,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_full_view);
        close=findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Dairy_tab.class));
            }
        });
        title=findViewById(R.id.Title_full);
        date=findViewById(R.id.date_full);
        msg=findViewById(R.id.edit_msg);
        save=findViewById(R.id.save_changes);
        tit=getIntent().getStringExtra("title");
        dat=getIntent().getStringExtra("date");
        text=getIntent().getStringExtra("text");
        title.setText(tit);
        date.setText(dat);
        //msg.setHint(text);
        msg.setText(text);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Diary").child(user.getUid()).child(tit).setValue(new diary_model(tit,dat,msg.getText().toString()));
                startActivity(new Intent(getApplicationContext(),Dairy_tab.class));
            }
        });

    }

}