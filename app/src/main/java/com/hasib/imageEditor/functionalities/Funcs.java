package com.hasib.imageEditor.functionalities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.hasib.imageEditor.MainActivity;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.io.InputStream;

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


//    TODO: GrayScale filter
    public static Bitmap filter(Bitmap image){
        Mat matImage = new Mat();
        Utils.bitmapToMat(image, matImage); // convert Bitmap to mat

        Mat filter = new Mat(); //mat object to store grayScale image
        Imgproc.cvtColor(matImage, filter, Imgproc.COLOR_BGR2GRAY); //convert image to grayscale

        Utils.matToBitmap(filter, image);
        return image;

    }

}
