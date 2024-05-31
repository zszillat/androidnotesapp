package com.zjs.notesapp.recyclerview;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjs.notesapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textViewFile;
    ImageButton imageButtonDelete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewFile = itemView.findViewById(R.id.textViewNoteName);
        imageButtonDelete = itemView.findViewById(R.id.imageButtonTrash);
    }

}
