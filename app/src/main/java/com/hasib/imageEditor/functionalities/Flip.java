package com.hasib.imageEditor.functionalities;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Flip {
    static boolean flipped = false;
    public static Bitmap flipImage(Bitmap image){
        Matrix matrix = new Matrix();
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
}
