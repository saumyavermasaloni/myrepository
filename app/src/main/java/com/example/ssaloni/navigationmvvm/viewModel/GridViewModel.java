

package com.example.ssaloni.navigationmvvm.viewModel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ssaloni.navigationmvvm.Adapter.ImageAdapter;
import com.example.ssaloni.navigationmvvm.Util.Contact;
import com.example.ssaloni.navigationmvvm.Util.DBHelper;
import com.example.ssaloni.navigationmvvm.Util.GridSpacingItemDecoration;
import com.example.ssaloni.navigationmvvm.viewModel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class GridViewModel extends BaseViewModel
{
    ImageAdapter imageAdapter;
    private final List<Contact> messagesList = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    RecyclerView imageRecycler;
    DataListener dataListener;
    DBHelper dbHelper;
    public GridViewModel(Context context,DataListener dataListener)
    {
        super(context);
        this.dataListener= dataListener;
        imageRecycler=dataListener.getRecyclerView();
        imageAdapter = new ImageAdapter(messagesList,context);
        gridLayoutManager = new GridLayoutManager(context,3);
        imageRecycler.setHasFixedSize(true);
        imageRecycler.setLayoutManager(gridLayoutManager);
        int spanCount = 3; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;
        imageRecycler.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        imageRecycler.setAdapter(imageAdapter);
        dbHelper = new DBHelper(context);
        loadAllImages();
    }

    public void loadAllImages()
    {
        try {
            dbHelper.open();
            List<Contact> contacts = dbHelper.getAllContacts();
            for (Contact cn : contacts) {
                String log = "ID:" + cn.getId() + " Image_url: " + cn.getImage_url();
                Log.e("Result: ", log);
                messagesList.add(cn);
            }
            dbHelper.close();
        } catch (Exception ioe) {
            dbHelper.close();

        }
    }
    public interface DataListener
    {
        RecyclerView getRecyclerView();
    }
}