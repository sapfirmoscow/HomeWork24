package ru.sberbank.homework24.pager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ru.sberbank.homework24.R;

public class ViewPagerAdapter extends PagerAdapter {

    private List<String> imageList;

    public ViewPagerAdapter(List<String> list) {
        imageList = new ArrayList<>();
        imageList.addAll(list);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.image_item, container, false);
        ImageView imageView = view.findViewById(R.id.viewpager_image);

        Picasso.get().load(new File(imageList.get(position)))
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
