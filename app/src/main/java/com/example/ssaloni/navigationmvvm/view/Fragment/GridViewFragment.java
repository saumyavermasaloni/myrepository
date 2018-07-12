package com.example.ssaloni.navigationmvvm.view.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ssaloni.navigationmvvm.Helper.OnStartDragListener;
import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.databinding.AllImagesBinding;
import com.example.ssaloni.navigationmvvm.viewModel.GridViewModel;


public class GridViewFragment extends BaseFragment implements GridViewModel.DataListener,OnStartDragListener
{
    GridViewModel gridViewModel;
    AllImagesBinding allImagesBinding;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        allImagesBinding = DataBindingUtil.inflate(inflater, R.layout.all_images, container, false);
        return allImagesBinding.getRoot();
    }

    public static GridViewFragment newInstance()
    {
        return new GridViewFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        gridViewModel = new GridViewModel(context,this);
        allImagesBinding.setViewModel(gridViewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        parentActivity.setTitle("Grid Fragment");
    }

    @Override
    public RecyclerView getRecyclerView()
    {
        return allImagesBinding.allimages;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder)
    {
        gridViewModel.onStartDrag(viewHolder);
    }

}


