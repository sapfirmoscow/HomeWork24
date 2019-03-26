package ru.sberbank.homework24.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import ru.sberbank.homework24.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;

    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.imageView);

    }

    public void setData(String path) {
        Picasso.get()
                .load(new File(path))
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(mImageView);
    }

    public void setListener(final int pos, final ArrayList<String> list, final AdapterOnClick onClick) {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(pos, list);
            }
        });
    }
}
