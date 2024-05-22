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

import com.zjs.notesapp.R;

import java.io.File;
import java.util.List;

public class EditNote extends AppCompatActivity {

    TextView textViewSave;
    EditText editTextFileName;
    EditText editTextNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);



        //Bind Elements
        textViewSave = this.findViewById(R.id.textViewSave);
        editTextFileName = this.findViewById(R.id.editTextFileName);
        editTextNote = this.findViewById(R.id.editTextNote);

        //Get Intent
        Intent intent = getIntent();
        String filepath = intent.getStringExtra("filePath");

        //Create Note Object from Intent
        Note n = new Note(intent.getStringExtra("filename"), intent.getStringExtra("fileContent"), null);

        //Set the EditText Content
        editTextFileName.setText(n.getFile());
        editTextNote.setText(n.getContent());

        //OnClickListener for SAVE Button
        textViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent for going back to main activity
                Intent intent = new Intent(EditNote.this, MainActivity.class);
                //file name text
                String editTextFileText = editTextFileName.getText().toString();
                //update in the Note file
                n.setContent(editTextNote.getText().toString());

                //Bool to make sure you are not overwriting another file
                boolean safeToWrite = true;

                if (!editTextFileText.equals(n.getFile())) {
                    intent.putExtra("delete", filepath);
                    List<Note> notes = Note.getFiles(EditNote.this, "notes");
                    for (Note existingNote : notes) {
                        if (existingNote.getFile().equals(editTextFileText)) {
                            safeToWrite = false;
                        }
                    }
                }

                if (safeToWrite) {
                    n.setFile(editTextFileName.getText().toString());
                    n.save(EditNote.this);

                    startActivity(intent);
                }


            }
        });

    }
}