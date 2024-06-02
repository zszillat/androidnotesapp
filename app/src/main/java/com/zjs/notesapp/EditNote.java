package com.zjs.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

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
        boolean newFile = intent.getBooleanExtra("newFile",false);

        editTextFileName.setText(filename);
        editTextNote.setText(content);

        textViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSafeToSave = true;

                for (File f : MainActivity.getFiles(EditNote.this)) {
                    if (f.getName().equals(editTextFileName.getText().toString()) && !f.getName().equals(filename)) {
                        isSafeToSave = false;
                    }
                }

                if (isSafeToSave) {
                    MainActivity.writeFile(
                            editTextFileName.getText().toString(),
                            editTextNote.getText().toString(),
                            EditNote.this
                    );

                    if (!newFile) {
                        if (!filename.equals(editTextFileName.getText().toString())) {
                            for (File f : MainActivity.getFiles(EditNote.this)) {
                                if (f.getName().equals(filename)) {
                                    f.delete();
                                }
                            }
                        }
                    }

                    startActivity(new Intent(EditNote.this, MainActivity.class));
                } else {
                    Toast.makeText(EditNote.this, "File Already Exists", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}