package com.zjs.notesapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjs.notesapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView filename;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        filename = itemView.findViewById(R.id.textViewFileName);
    }
}
