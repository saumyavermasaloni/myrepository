package com.example.ssaloni.navigationmvvm.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.Util.DBHelper;
import com.example.ssaloni.navigationmvvm.databinding.ActivityMainBinding;
import com.example.ssaloni.navigationmvvm.view.Fragment.GallaryFragment;
import com.example.ssaloni.navigationmvvm.viewModel.MainActivityViewModel;


public class MainActivity extends BaseActivity implements MainActivityViewModel.DataListener,
        NavigationView.OnNavigationItemSelectedListener


{
    MainActivityViewModel viewModel;
    ActivityMainBinding binding;
    private Fragment customFragment;
    private GallaryFragment fragment;
    static final int CAM_REQ=1;

    public Bitmap mImageBitmap;
    public String mCurrentPhotoPath;
    public ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel=new MainActivityViewModel(this,this);
        binding.setViewModel(viewModel);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        binding.drawerLayout.closeDrawers();
        return viewModel.onNavigationItemSelected(menuItem, getSupportFragmentManager().beginTransaction());

    }

    @Override
    public void startNewActivity(Intent startIntent)
    {

    }

    @Override
    public void replaceFragment(Fragment fragment) {
        customFragment = fragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}