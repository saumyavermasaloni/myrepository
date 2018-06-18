

package com.example.ssaloni.navigationmvvm.viewModel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.ssaloni.navigationmvvm.Adapter.CustomerListAdapter;
import com.example.ssaloni.navigationmvvm.Adapter.ImageAdapter;
import com.example.ssaloni.navigationmvvm.Helper.OnStartDragListener;
import com.example.ssaloni.navigationmvvm.Helper.SimpleItemTouchHelperCallback;
import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.Util.Contact;
import com.example.ssaloni.navigationmvvm.Util.DBHelper;
import com.example.ssaloni.navigationmvvm.Util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridViewModel extends BaseViewModel
{
    private ObservableField<ImageView> onZoom = new ObservableField<ImageView>();

    ImageAdapter imageAdapter;
    private final List<Contact> messagesList = new ArrayList<>();
    DataListener dataListener;
    RecyclerView imageRecycler;
    GridLayoutManager gridLayoutManager;
    DBHelper dbHelper;
    GridView gridView;
    RecyclerView.LayoutManager mLayoutManager;
    private CustomerListAdapter mAdapter;
    OnStartDragListener dragStartListener;
    private ItemTouchHelper mItemTouchHelper;
    private Animator mCurrentAnimatorEffect;
    private int mShortAnimationDurationEffect;

    public GridViewModel(Context context,DataListener dataListener)
    {
        super(context);
        final CustomerListAdapter adapter = new CustomerListAdapter(context,messagesList,dragStartListener);

        RecyclerView recyclerView=dataListener.getRecyclerView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        final int spanCount = 3;
        final StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        dbHelper = new DBHelper(context);
        loadAllImages();


      /* imageRecycler = dataListener.getRecyclerView();
        imageRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        imageRecycler.setLayoutManager(mLayoutManager);
        //mCustomers = SampleData.addSampleCustomers();

        //setup the adapter with empty list
        mAdapter = new CustomerListAdapter(context, messagesList, dragStartListener);
        gridLayoutManager = new GridLayoutManager(context,3);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(imageRecycler);
        int spanCount = 3; //  columns
        int spacing = 1; // 50px
        boolean includeEdge = true;
        imageRecycler.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        imageRecycler.setAdapter(mAdapter);
        dbHelper = new DBHelper(context);
        loadAllImages();*/

        /*super(context);
        this.dataListener= dataListener;
        imageRecycler=dataListener.getRecyclerView();
        imageAdapter = new ImageAdapter(messagesList,context);
        gridLayoutManager = new GridLayoutManager(context,3);
        imageRecycler.setHasFixedSize(true);
        imageRecycler.setLayoutManager(gridLayoutManager);
        int spanCount = 3; //  columns
        int spacing = 1; // 50px
        boolean includeEdge = true;
        imageRecycler.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        imageRecycler.setAdapter(imageAdapter);
        dbHelper = new DBHelper(context);
        loadAllImages();*/

       /* super(context);
        this.dataListener= dataListener;
        gridView = dataListener.getGridView();
        imageAdapter=new ImageAdapter(context,messagesList);
        gridView.setAdapter(imageAdapter);
        dbHelper = new DBHelper(context);
        loadAllImages();*/
    }


  /*  private void zoomImageFromThumb(final ObservableField<View> thumbView,int imageResId)
    {
        if (mCurrentAnimatorEffect != null)
        {
            mCurrentAnimatorEffect.cancel();
        }
        final ImageView expandedImageView = dataListener.getImageView();
        expandedImageView.setImageResource(imageResId);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

    *//*    thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);*//*

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height())
        {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        }
        else
        {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        //thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDurationEffect);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimatorEffect = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimatorEffect = null;
            }
        });
        set.start();
        mCurrentAnimatorEffect = set;

        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimatorEffect != null) {
                    mCurrentAnimatorEffect.cancel();
                }

                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDurationEffect);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        //thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimatorEffect = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation)
                    {
                        //thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimatorEffect = null;
                    }
                });
                set.start();
                mCurrentAnimatorEffect = set;
            }
        });
    }*/

    public void loadAllImages()
    {
        try
        {
            dbHelper.open();
            List<Contact> contacts = dbHelper.getAllContacts();
            for (Contact cn : contacts)
            {
                messagesList.add(cn);
            }
            dbHelper.close();
        }
        catch (Exception ioe)
        {
            dbHelper.close();
        }
    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder)
    {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public ObservableField<ImageView> getOnZoom() {
        return onZoom;
    }

    public void setOnZoom(ObservableField<ImageView> onZoom) {
        this.onZoom = onZoom;
    }

    public void zoomImageFromThumb()
    {
        //final ImageView expandedImageView =dataListener.getExpandedView();
        //expandedImageView.setImageResource(imageView1);
    }


    public interface DataListener
    {
        //GridView getGridView();
        RecyclerView getRecyclerView();
        ImageView getExpandedView();
    }
}