package com.zjs.notesapp.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjs.notesapp.EditNote;
import com.zjs.notesapp.MainActivity;
import com.zjs.notesapp.Note;
import com.zjs.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{

    List<Note> notes = new ArrayList<>();
    Context context;

    private AdapterView.OnItemClickListener listener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public Adapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_file_folder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.filename.setText(notes.get(position).getFile());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note n = notes.get(holder.getAdapterPosition());
                if (!n.isDir()) {
                    Intent intent = new Intent(context, EditNote.class);
                    intent.putExtra("filename", n.getFile());
                    intent.putExtra("fileContent", n.getContent());
                    context.startActivity(intent);
                } else {
                    //TODO changing directory logic
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
