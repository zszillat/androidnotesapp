package com.zjs.notesapp.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjs.notesapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView filename;
    Button buttonDelete;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        filename = itemView.findViewById(R.id.textViewFileName);
        buttonDelete = itemView.findViewById(R.id.buttonDelete);
    }
}
