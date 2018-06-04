package com.example.ssaloni.navigationmvvm.view.Fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.Util.DBHelper;
import com.example.ssaloni.navigationmvvm.Util.Utils;
import com.example.ssaloni.navigationmvvm.databinding.FragmentGallaryBinding;
import com.example.ssaloni.navigationmvvm.view.Activity.MainActivity;
import com.example.ssaloni.navigationmvvm.viewModel.GalleryViewModel;
import com.example.ssaloni.navigationmvvm.viewModel.MainActivityViewModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CoderResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by ssaloni on 5/11/2018.
 */

public class GallaryFragment extends BaseFragment implements GalleryViewModel.DataListener
{
    GalleryViewModel viewModel;
    FragmentGallaryBinding fragmentGallaryBinding;
    Uri picture;

    private static final int SELECT_PICTURE = 100;
    private static final int CAMERA_REQUEST = 1888;
    private String imageFilePath = "";
    File photoFile = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentGallaryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallary, container, false);
        return fragmentGallaryBinding.getRoot();
    }

    public static GallaryFragment newInstance() {
        return new GallaryFragment();
    }

    Drawable drawable;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new GalleryViewModel(context,  this,picture);
        fragmentGallaryBinding.setViewModel(viewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parentActivity.setTitle("Gallary Fragment");
    }

    public static Intent getStartIntent(Context context)
    {
        Intent intent=new Intent(context,GallaryFragment.class);
        return intent;
    }

    @Override
    public void startNewActivity(Intent startIntent)
    {

    }

    @Override
    public void openImageChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void openCamera()
    {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        else
        {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
                // Create the File where the photo should go
                try {

                    photoFile = createImageFile();
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(context,
                                "com.example.ssaloni.navigationmvvm.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                    }
                } catch (Exception ex) {
                    // Error occurred while creating the File
                    displayMessage(context,ex.getMessage().toString());
                }


            }else

            {
                displayMessage(context,"Nullll");
            }
        }
    }
    private File createImageFile() throws IOException{

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
    }

    private void displayMessage(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SELECT_PICTURE:
                viewModel.onResultReceived(requestCode, resultCode, data,"");
                break;
            case CAMERA_REQUEST:
                viewModel.onResultReceived(requestCode, resultCode, data,photoFile.getAbsolutePath());
                break;
        }

    }

}