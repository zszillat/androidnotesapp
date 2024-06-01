package com.zjs.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditNote extends AppCompatActivity {

    EditText editTextFileName;
    EditText editTextNote;
    TextView textViewSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        textViewSave = this.findViewById(R.id.textViewSave);
        editTextFileName = this.findViewById(R.id.editTextFileName);
        editTextNote = this.findViewById(R.id.editTextNote);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");
        String content = intent.getStringExtra("fileContent");

        editTextFileName.setText(filename);
        editTextNote.setText(content);

        textViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            MainActivity.writeFile(
                    editTextFileName.getText().toString(),
                    editTextNote.getText().toString(),
                    EditNote.this
            );

            startActivity(new Intent(EditNote.this, MainActivity.class));
            }
        });


    }
}