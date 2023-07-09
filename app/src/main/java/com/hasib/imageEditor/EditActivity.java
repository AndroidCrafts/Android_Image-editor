package com.hasib.imageEditor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.hasib.flipimage.databinding.ActivityEditBinding;
import com.hasib.imageEditor.functionalities.Funcs;

import java.io.IOException;
import java.io.InputStream;

public class EditActivity extends AppCompatActivity {
    private Bitmap image;
    private Bitmap editedImage;
    private boolean flipped = false;
    private final Matrix matrix = new Matrix();
    private ActivityEditBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setSupportActionBar(b.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getSelectedImage();

//        Flip Button
        b.flipImage.setOnClickListener(v -> {
            editedImage = Funcs.flipImage(image);
           b.selectedImage.setImageBitmap(editedImage);
        });
//        Rotate Button
        b.rotateImage.setOnClickListener(v -> {
            editedImage = Funcs.rotateImage(image);
            b.selectedImage.setImageBitmap(editedImage);
        });
//        Filter btn
        b.filter.setOnClickListener(v -> {
            editedImage = Funcs.filter(image);
            b.selectedImage.setImageBitmap(editedImage);
        });

//        Paint btn
        b.paint.setOnClickListener(v -> {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            double x = dm.widthPixels;
            double y = dm.heightPixels;

            editedImage = Funcs.paintCircle(image);
            b.selectedImage.setImageBitmap(editedImage);
        });

//        TODO:// Save Image
        b.saveBtn.setOnClickListener(v -> {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    editedImage, "flippedImage.jpg", "Image Flipped");
            Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void getSelectedImage(){
        try {
            InputStream inputStream = getContentResolver().openInputStream(MainActivity.uri);
            image = BitmapFactory.decodeStream(inputStream);
            b.selectedImage.setImageBitmap(image);
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}