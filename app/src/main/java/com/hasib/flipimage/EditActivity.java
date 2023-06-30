package com.hasib.flipimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hasib.flipimage.Sugar.Sugar;

import java.io.IOException;
import java.io.InputStream;

public class EditActivity extends AppCompatActivity {

    private Sugar sugar;
    private ImageView selectedImage;
    private ImageButton flipImageButton;
    private Bitmap image;
    Bitmap flippedImage;
    private Button saveButton;
    private boolean flipped = false;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        selectedImage = findViewById(R.id.selectedImage);
        flipImageButton = findViewById(R.id.flip_image);
        saveButton = findViewById(R.id.save_btn);
        progressBar = findViewById(R.id.progress_bar);
        sugar = new ViewModelProvider(this).get(Sugar.class);
        getSelectedImage();

//        TODO: Flip Image
        flipImageButton.setOnClickListener(v -> {
            Matrix matrix = new Matrix();
            if(!flipped){
                matrix.preScale(-1, 1);
                flipped = true;
            }else {
                matrix.preScale(1, 1);
                flipped = false;
            }
            flippedImage = Bitmap.createBitmap(this.image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            selectedImage.setImageBitmap(flippedImage);
        });

//        TODO:// Save Image
        saveButton.setOnClickListener(v -> {
            MediaStore.Images.Media.insertImage(getContentResolver(), flippedImage, "flippedImage", "Image Flipped");
            Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void getSelectedImage(){
        try {
            InputStream inputStream = getContentResolver().openInputStream(MainActivity.uri);
            image = BitmapFactory.decodeStream(inputStream);
            selectedImage.setImageBitmap(image);
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}