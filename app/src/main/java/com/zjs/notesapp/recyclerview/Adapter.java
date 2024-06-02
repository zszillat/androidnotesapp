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
import com.zjs.notesapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    File[] files;
    Context context;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
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
                f.delete();
                List<File> fileList = new ArrayList<>(Arrays.asList(files));
                fileList.remove(f);
                files = fileList.toArray(new File[0]);

                notifyItemRemoved(position);
                // Call this method to update the view for the remaining items
                notifyItemRangeChanged(position, files.length);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditNote.class);
                intent.putExtra("filename", f.getName());
                intent.putExtra("fileContent", MainActivity.readFileAsString(f));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.length;
    }


}
