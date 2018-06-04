package com.example.ssaloni.navigationmvvm.viewModel;

import android.content.Context;

/**
 * Created by ssaloni on 5/11/2018.
 */

public abstract class BaseViewModel
{
    Context context;
    public BaseViewModel(Context context)
    {
        this.context=context;
    }

    public Context getContext() {
        return context;
    }
}