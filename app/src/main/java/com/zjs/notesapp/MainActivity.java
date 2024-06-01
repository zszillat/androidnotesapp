package com.zjs.notesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zjs.notesapp.recyclerview.Adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButtonAdd = this.findViewById(R.id.floatingActionButtonAdd);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNote.class);
                intent.putExtra("filename", "New Note");
                intent.putExtra("fileContent", "");
                startActivity(intent);
            }

        });

        recyclerView = this.findViewById(R.id.recyclerViewFiles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(this, getFiles());
        recyclerView.setAdapter(adapter);

        for (File f : getFiles()) {
            System.out.println(f.getName());
        }

    }

    public File[] getFiles() {
        String directoryName = "notes";
        File[] filesList = null;

        // Getting the internal storage directory
        File internalStorageDir = getFilesDir();
        File notesDir = new File(internalStorageDir, directoryName);

        // Check if the directory exists
        if (notesDir.exists()) {
            // Get the list of file objects
            filesList = notesDir.listFiles();
        }

        return filesList;
    }

    public static void writeFile(String fileName, String data, Context context) {
        String directoryName = "notes";
        FileOutputStream fos = null;

        try {
            // Getting the internal storage directory
            File internalStorageDir = context.getFilesDir();
            File notesDir = new File(internalStorageDir, directoryName);

            // Create the directory if it does not exist
            if (!notesDir.exists()) {
                notesDir.mkdir();
            }

            // Create a File object for the output file
            File outputFile = new File(notesDir, fileName);

            // Open a FileOutputStream for writing to the outputFile
            fos = new FileOutputStream(outputFile);

            // Write data to the file
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the FileOutputStream
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFileAsString(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }


}