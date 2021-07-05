package com.example.trackntriggery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_diary extends AppCompatActivity {
    EditText title_edit,date_edit,note_edit;
    Button add_note;
    diary_model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary);
        title_edit=findViewById(R.id.title_edit);
        date_edit=findViewById(R.id.date_edit);
        note_edit=findViewById(R.id.note_edit);
        add_note=findViewById(R.id.add_button_diary);

        model=new diary_model(title_edit.getText().toString(),date_edit.getText().toString(),note_edit.getText().toString());
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                //databaseReference.child("Diary").child(user.getUid()).child(title_edit.getText().toString()).setValue(new diary_model(title_edit.getText().toString(),date_edit.getText().toString(),note_edit.getText().toString()));
                startActivity(new Intent(getApplicationContext(), Dairy_tab.class));
            }
        });
    }
}