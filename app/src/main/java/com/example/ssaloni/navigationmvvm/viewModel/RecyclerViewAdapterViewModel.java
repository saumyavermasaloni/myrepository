package com.example.ssaloni.navigationmvvm.viewModel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ssaloni on 6/18/2018.
 */

public class RecyclerViewAdapterViewModel extends BaseViewModel
{

    public RecyclerViewAdapterViewModel(Context context)
    {
        super(context);
    }

    public void onZoom()
    {
        Toast.makeText(context,"BYEEE",Toast.LENGTH_LONG).show();
    }

    public interface DataListener
    {

    }
}
