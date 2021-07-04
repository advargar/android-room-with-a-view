package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateWord extends AppCompatActivity {

    EditText mWord_input;
    Button update_button;
    String mWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_word);

        mWord_input = findViewById(R.id.edit_word);
        update_button = findViewById(R.id.button_update);
        update_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
            }
        });
        getandSetIntentWord();
    }
    
    void getandSetIntentWord() {
        if(getIntent().hasExtra("mWord")){
            //GET
            mWord = getIntent().getStringExtra("mWord");

            //SET
            mWord_input.setText(mWord);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnCancel(View view) {
    }
}