package com.zjs.notesapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjs.notesapp.R;

import java.io.File;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    File[] files;
    Context context;
    private AdapterView.OnItemClickListener listener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public Adapter(Context context, File[] files) {
        this.context = context;
        this.files = files;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File f = files[position];
        holder.textViewFile.setText(f.getName());

        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Delete Logic
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Item View Click Logic
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.length;
    }


}
