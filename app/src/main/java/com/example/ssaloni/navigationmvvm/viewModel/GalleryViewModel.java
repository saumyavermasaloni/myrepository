package com.example.ssaloni.navigationmvvm.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ssaloni.navigationmvvm.Util.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.databinding.adapters.ImageViewBindingAdapter.setImageUri;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by ssaloni on 5/11/2018.
 */

public class GalleryViewModel extends BaseViewModel
{
    DataListener dataListener;
    private static final int SELECT_PICTURE = 100;
    private static final int CAMERA_REQUEST = 1888;
    DBHelper dbHelper=new DBHelper(context);

    private ObservableField<Uri> imageData = new ObservableField<Uri>();


    public GalleryViewModel(Context context, DataListener dataListener, Uri picture)
    {
        super(context);
        this.dataListener=dataListener;
        imageData.set(picture);
    }

    public ObservableField<Uri> getImageData()
    {
        return imageData;
    }

    public void setImageData(ObservableField<Uri> imageData)
    {
        this.imageData = imageData;
    }

    public View.OnClickListener onOpenCamera()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dataListener.openCamera();
            }
        };
    }

    public View.OnClickListener onOpenGallery()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dataListener.openImageChooser();
            }
        };
    }

    public void onResultReceived(int requestCode, int resultCode, Intent data, String imageFilePath)
    {
        switch (requestCode){
            case CAMERA_REQUEST:
                try{
                    if(resultCode==RESULT_OK) {
                        // Saving to Database...
                        Uri cameraUri = Uri.parse(imageFilePath);
                        if(saveImageInDB(cameraUri)) {

                        imageData.set(cameraUri);
                        Log.i("info", String.valueOf(cameraUri));}}}
                catch (Exception e) {
                    e.printStackTrace();}
                break;


            case SELECT_PICTURE:
                try
                {
                    if(resultCode == RESULT_OK)
                    {
                        Uri selectedImageUri = data.getData();
                            // Saving to Database...
                            if (saveImageInDB(selectedImageUri))
                            {
                                imageData.set(selectedImageUri);
                               // Log.i("info", String.valueOf(selectedImageUri));
                            }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }


    Boolean saveImageInDB(Uri selectedImage) {
        dbHelper.open();
        dbHelper.insertImageUrl(String.valueOf(selectedImage));
        //Log.i("info", String.valueOf(selectedImage));
        dbHelper.close();
        return true;
    }

    public interface DataListener
    {
        void startNewActivity(Intent startIntent);
        void openImageChooser();
        void openCamera();
    }
}
