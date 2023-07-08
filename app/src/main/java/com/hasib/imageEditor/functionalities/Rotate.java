package com.hasib.imageEditor.functionalities;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Rotate {
    public static Bitmap rotateImage(Bitmap image){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        return Bitmap.createBitmap(image, 0, 0,
                image.getWidth(), image.getHeight(), matrix, true);
    }
}
