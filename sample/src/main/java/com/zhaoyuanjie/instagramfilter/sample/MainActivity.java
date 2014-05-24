package com.zhaoyuanjie.instagramfilter.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zhaoyuanjie.instagramfilter.library.GLLayer;
import com.zhaoyuanjie.instagramfilter.library.InstagramFilterView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends Activity {

    private static final int PICK_IMAGE = 100;

    private InstagramFilterView photo;
    private View button;

    private MenuItem mItemCapture0;
    private MenuItem mItemCapture1;
    private MenuItem mItemCapture2;
    private MenuItem mItemCapture3;
    private MenuItem mItemCapture4;
    private MenuItem mItemCapture5;
    private MenuItem mItemCapture6;
    private MenuItem mItemCapture7;
    private MenuItem mItemCapture8;
    private MenuItem mItemCapture9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = (InstagramFilterView) findViewById(R.id.photo);

        button = findViewById(R.id.pick);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream= getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
            }
            if (imageStream != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(imageStream, null, options);
                int width = options.outWidth;
                int height = options.outHeight;
                Rect rect = new Rect(0, 0, width, height);
                int offset = Math.abs((width - height) / 2);
                if (rect.width() > rect.height()) {
                    rect.left += offset;
                    rect.right -= offset;
                } else {
                    rect.top += offset;
                    rect.bottom -= offset;
                }
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream, rect, null);
                photo.setBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mItemCapture0 = menu.add("Origin");
        mItemCapture1 = menu.add("Amaro");
        mItemCapture2= menu.add("Earlybird");
        mItemCapture3 = menu.add("Hefe");
        mItemCapture4 = menu.add("Hudson");
        mItemCapture5 = menu.add("Mayfair");
        mItemCapture6= menu.add("Rise");
        mItemCapture7 = menu.add("Toaster");
        mItemCapture8= menu.add("Willow");
        mItemCapture9 = menu.add("Xpro");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == mItemCapture0) {
            photo.setFilter(GLLayer.NONE);
            return true;
        }
        if (item == mItemCapture1) {
            photo.setFilter(GLLayer.AMARO);
            return true;
        }
        if	(item == mItemCapture2) {
            photo.setFilter(GLLayer.EARLYBIRD);
            return true;
        }
        if	(item == mItemCapture3) {
            photo.setFilter(GLLayer.HEFE);
            return true;
        }
        if  (item == mItemCapture4) {
            photo.setFilter(GLLayer.HUDSON);
            return true;
        }
        if (item == mItemCapture5) {
            photo.setFilter(GLLayer.MAYFAIR);
            return true;
        }
        if	(item == mItemCapture6) {
            photo.setFilter(GLLayer.RISE);
            return true;
        }
        if	(item == mItemCapture7) {
            photo.setFilter(GLLayer.TOASTER);
            return true;
        }
        if	(item == mItemCapture8) {
            photo.setFilter(GLLayer.WILLOW);
            return true;
        }
        if	(item == mItemCapture9) {
            photo.setFilter(GLLayer.XPRO);
            return true;
        }

        return false;
    }
}
