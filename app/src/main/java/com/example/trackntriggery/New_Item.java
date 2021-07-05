package com.example.trackntriggery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class New_Item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    model_inventory model;
    EditText name,quantity,i_url;
    Spinner spinner;
    Button add;
    String cat="";
    String n,q,i,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__item);
        spinner = (Spinner) findViewById(R.id.category_array);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        add=findViewById(R.id.add_button);
        name=findViewById(R.id.name_edit);
        quantity=findViewById(R.id.q_edit);
        i_url=findViewById(R.id.i_url);
        n=name.getText().toString();
        //
        q=quantity.getText().toString();
        //
        i=i_url.getText().toString();
        //
        model=new model_inventory(n,q,i,cat);
        /*model.setName(name.getText().toString());
        model.setQuantity(quantity.getText().toString());
        model.setImage_url(i_url.getText().toString());*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                databaseReference.child(cat).child(user.getUid()).child(name.getText().toString()).setValue(new model_inventory(name.getText().toString(),quantity.getText().toString(),i_url.getText().toString(),cat));

                startActivity(new Intent(getApplicationContext(),Track_tabbed.class));
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.category_array){
            cat=parent.getItemAtPosition(position).toString();
            model.setCategory(cat);
            Log.d("CAT",cat);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}