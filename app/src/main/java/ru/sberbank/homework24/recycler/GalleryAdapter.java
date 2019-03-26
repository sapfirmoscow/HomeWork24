package ru.sberbank.homework24.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.homework24.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

    private List<String> mData;
    private AdapterOnClick mOnClick;

    public GalleryAdapter(List<String> data, AdapterOnClick adapterOnClick) {
        mData = data;
        mOnClick = adapterOnClick;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_item, viewGroup, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder galleryViewHolder, int i) {
        galleryViewHolder.setData(mData.get(i));
        galleryViewHolder.setListener(i, (ArrayList<String>) mData, mOnClick);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
