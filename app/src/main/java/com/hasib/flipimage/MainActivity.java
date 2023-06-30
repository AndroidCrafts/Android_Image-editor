package com.hasib.flipimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hasib.flipimage.Sugar.Sugar;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 1;
    private Sugar sugar;

    public static Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sugar = new ViewModelProvider(this).get(Sugar.class);

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            uri = data.getData();
            Intent editIntent = new Intent(this, EditActivity.class);
            startActivity(editIntent);
        }
    }
}