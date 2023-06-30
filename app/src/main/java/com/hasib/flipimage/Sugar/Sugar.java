package com.hasib.flipimage.Sugar;

import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModel;


public class Sugar extends ViewModel {
    private Uri imageUri;

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public static void progressBar(ProgressBar progressBar, boolean state){
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
