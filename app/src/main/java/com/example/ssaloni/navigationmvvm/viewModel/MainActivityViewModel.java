package com.example.ssaloni.navigationmvvm.viewModel;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.view.Fragment.GallaryFragment;
import com.example.ssaloni.navigationmvvm.view.Fragment.GridViewFragment;

/**
 * Created by ssaloni on 5/11/2018.
 */

public class MainActivityViewModel extends BaseViewModel
{
    DataListener dataListener;
    private MenuItem mPreviousMenuItem;

    public MainActivityViewModel(Context context, DataListener dataListener) {
        super(context);
        this.dataListener=dataListener;
    }

    public boolean onNavigationItemSelected(MenuItem menuItem, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {
        menuItem.setCheckable(true);
        menuItem.setChecked(true);

        if (mPreviousMenuItem != null) {
            mPreviousMenuItem.setChecked(false);
        }
        mPreviousMenuItem = menuItem;
        switch (menuItem.getItemId())
        {
            case R.id.nav_gallery:

                GallaryFragment gallaryFragment = GallaryFragment.newInstance();
                dataListener.replaceFragment(gallaryFragment);
                break;

            case R.id.grid_View:

                GridViewFragment gridFragment = GridViewFragment.newInstance();
                dataListener.replaceFragment(gridFragment);
                break;

            default:
                break;
        }
        return true;
    }


    public interface DataListener
    {
        void startNewActivity(Intent startIntent);

        void replaceFragment(Fragment fragment);
    }
}
