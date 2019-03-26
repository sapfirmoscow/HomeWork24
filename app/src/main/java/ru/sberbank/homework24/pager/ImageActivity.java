package ru.sberbank.homework24.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.homework24.R;

public class ImageActivity extends AppCompatActivity {

    private static final String IMAGES_KEY = "images_key";
    private static final String POSITION_KEY = "position_key";

    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private List<String> mList;

    public static final Intent newIntent(Context context, ArrayList<String> images, int pos) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(IMAGES_KEY, images);
        intent.putExtra(POSITION_KEY, pos);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initViews();
        init();
    }

    private void init() {
        mList = new ArrayList<>();
        mList.addAll((ArrayList<String>) getIntent().getSerializableExtra(IMAGES_KEY));
        mAdapter = new ViewPagerAdapter(mList);
        mPager.setAdapter(mAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        mPager.setCurrentItem(getIntent().getIntExtra(POSITION_KEY, 0));
    }

    private void initViews() {
        mPager = findViewById(R.id.viewPager);
    }
}
