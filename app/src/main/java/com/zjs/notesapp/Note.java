package com.zjs.notesapp;

import android.content.Context;
import android.os.Debug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Note {

    private String file;
    private String content;
    private boolean dir;

    public Note(String file, String content) {
        this.file = file;
        this.content = content;
        dir = false;
    }

    public Note(String file, String content, boolean dir) {
        this.file = file;
        this.content = content;
        this.dir = dir;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Note{" +
                "file='" + file + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void save(Context context) {
        String dirName = "notes";
        File directory = context.getFilesDir(); // Get the internal storage directory
        File newDir = new File(directory, dirName); // Create a File object for the new directory

        //check if notes directory exists
        if (!newDir.exists()) {
            boolean created = newDir.mkdir();
            if (created) { System.out.println("notes Directory Created"); }
        }

        // Create a file within the internal storage directory
        File file = new File(directory, dirName + "/" + this.file);

        // Write to the file
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(this.content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Note> getFiles(Context context, String filepath) {

        List<Note> notes = new ArrayList<>();
        String filename;

        // Get the directory of the internal storage for the app
        File directory = new File(context.getFilesDir(), filepath);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            // List all files in the directory
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Print the filename
                    filename = file.getName();

                    // Initialize a StringBuilder to hold the file contents
                    StringBuilder contents = new StringBuilder();

                    // Read and append the file contents to the StringBuilder
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            contents.append(line).append("\n"); // Append each line and a newline character
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    notes.add(new Note(filename, contents.toString(), file.isDirectory()));

                }
            }
        }

        return notes;
    }

}
