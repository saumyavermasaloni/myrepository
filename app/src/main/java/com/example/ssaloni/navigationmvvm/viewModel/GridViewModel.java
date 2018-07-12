

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

    private ObservableField<CustomerListAdapter> ImageObservableField = new ObservableField<>();

    private ObservableField<RecyclerView.LayoutManager> layoutManager = new ObservableField<>();

    public ObservableField<RecyclerView.LayoutManager> getLayoutManager()
    {
        return layoutManager;
    }

    public void setLayoutManager(ObservableField<RecyclerView.LayoutManager> layoutManager)
    {
        this.layoutManager = layoutManager;
    }

    public ObservableField<CustomerListAdapter> getImageObservableField() {
        return ImageObservableField;
    }

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
        CustomerListAdapter adapter = new CustomerListAdapter(messagesList,dragStartListener,context);

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

        adapter.notifyDataSetChanged();

        loadAllImages();
    }

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

    public interface DataListener
    {
        //GridView getGridView();
        RecyclerView getRecyclerView();
    }
}