package com.example.ssaloni.navigationmvvm.view.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ssaloni on 5/11/2018.
 */

public class BaseFragment extends Fragment
{

    protected AppCompatActivity parentActivity;
    protected Context context;
    public BaseFragment()
    {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parentActivity = (AppCompatActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

