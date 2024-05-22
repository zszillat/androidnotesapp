package com.zjs.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zjs.notesapp.recyclerview.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewFiles;
    String currentDirectory = "notes";
    List<Note> notes = new ArrayList<>();

    FloatingActionButton floatingActionButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gets intent to see if anything needs deleted
        Intent thisIntent = getIntent();
        String delete = thisIntent.getStringExtra("delete");

        //get a collection
        notes = Note.getFiles(this, currentDirectory);
        if (delete != null) {

            for (Note n : notes) {
                if (n.getFileObject().getAbsolutePath().equals(delete)) {
                    n.getFileObject().delete();

                }
            }
            //update notes now that a file has been deleted
            notes = Note.getFiles(this, currentDirectory);
        };

        //assign components
        RecyclerView recyclerView = this.findViewById(R.id.recyclerViewFiles);
        floatingActionButtonAdd = this.findViewById(R.id.floatingActionButtonAdd);

        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNote.class);
                intent.putExtra("filename", "New Note");
                intent.putExtra("fileContent", "");
                intent.putExtra("filePath", "");
                startActivity(intent);
            }
        });

        //set recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(this, notes);
        recyclerView.setAdapter(adapter);



    }
}