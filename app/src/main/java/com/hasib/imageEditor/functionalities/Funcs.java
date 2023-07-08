package com.hasib.imageEditor.functionalities;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Funcs {
    private static boolean flipped = false;
    private static final Matrix matrix = new Matrix();

//    TODO: Flip Image
    public static Bitmap flipImage(Bitmap image){
        if(!flipped){
            matrix.preScale(-1, 1);
            flipped = false;
        }else {
            matrix.preScale(1, 1);
            flipped = true;
        }

        return Bitmap.createBitmap(image, 0, 0,
                image.getWidth(), image.getHeight(), matrix, true);
    }


//    TODO: Rotate Image
    public static Bitmap rotateImage(Bitmap image){
        matrix.postRotate(90);

        return Bitmap.createBitmap(image, 0, 0,
                image.getWidth(), image.getHeight(), matrix, true);
    }
}
