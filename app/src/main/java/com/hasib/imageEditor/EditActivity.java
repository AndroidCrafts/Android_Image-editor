package com.hasib.imageEditor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import com.hasib.flipimage.databinding.ActivityEditBinding;
import java.io.IOException;
import java.io.InputStream;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
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
        b.flipImage.setOnClickListener(this);
//        Rotate Button
        b.rotateImage.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();

//        TODO: Rotate Image
            matrix.postRotate(90);
            editedImage = Bitmap.createBitmap(this.image, 0, 0,
                    image.getWidth(), image.getHeight(), matrix, true);
            b.selectedImage.setImageBitmap(editedImage);

    }
}