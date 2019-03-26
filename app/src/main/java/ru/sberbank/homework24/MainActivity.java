package ru.sberbank.homework24;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ru.sberbank.homework24.pager.ImageActivity;
import ru.sberbank.homework24.recycler.AdapterOnClick;
import ru.sberbank.homework24.recycler.GalleryAdapter;

public class MainActivity extends AppCompatActivity implements AdapterOnClick {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private GalleryAdapter mGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        checkPermission();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mGalleryAdapter = new GalleryAdapter(getGallery(), this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 5);
        recyclerView.setAdapter(mGalleryAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        } else {
            ///
        }
    }


    public ArrayList<String> getGallery() {
        ArrayList<String> listOfAllImages = new ArrayList<>();

        String[] projection = {MediaStore.MediaColumns.DATA};

        Cursor cursor = getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null,
                null, null);

        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()) {
            listOfAllImages.add(cursor.getString(column_index_data));
        }

        cursor.close();
        return listOfAllImages;
    }

    @Override
    public void onClick(int position, ArrayList<String> images) {
        startActivity(ImageActivity.newIntent(this, images, position));
    }
}
