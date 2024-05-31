package com.zjs.notesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zjs.notesapp.recyclerview.Adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void writeFile(String fileName, String data) {
        String directoryName = "notes";
        FileOutputStream fos = null;

        try {
            // Getting the internal storage directory
            File internalStorageDir = getFilesDir();
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

}