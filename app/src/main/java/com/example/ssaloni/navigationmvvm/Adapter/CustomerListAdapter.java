package com.example.ssaloni.navigationmvvm.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssaloni.navigationmvvm.Helper.ItemTouchHelperAdapter;
import com.example.ssaloni.navigationmvvm.Helper.ItemTouchHelperViewHolder;
import com.example.ssaloni.navigationmvvm.Helper.OnStartDragListener;
import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.Util.Contact;
import com.example.ssaloni.navigationmvvm.viewModel.GridViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by ssaloni on 6/11/2018.
 */
public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter
{
    public   List<Contact> mMessageList;

    public final OnStartDragListener mDragStartListener;
    public Context context;
    public int mShortAnimationDurationEffect;
    private Animator mCurrentAnimatorEffect;
    GridViewModel gridViewModel;

    public CustomerListAdapter(Context context, List<Contact> mMessageList, OnStartDragListener dragStartListener)
    {
        this.context=context;
        mDragStartListener = dragStartListener;
        this.mMessageList=mMessageList;
        //mItems.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items)));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder
    {
        public final ImageView messageImage;
        public final ImageView expandedImageView;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            messageImage = (ImageView) itemView.findViewById(R.id.imageView1);
            expandedImageView = (ImageView) itemView.findViewById(R.id.expandedimage);

            //handleView = (ImageView) itemView.findViewById(R.id.handle);
        }

        @Override
        public void onItemSelected()
        {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear()
        {
            itemView.setBackgroundColor(0);
        }
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position)
    {
        Contact c = mMessageList.get(position);
        holder.messageImage.setImageURI(Uri.parse(c.getImage_url()));

       // holder.textView.setText(mItems.get(position));

        // Start a drag whenever the handle view it touched
        holder.messageImage.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
                {
                    Toast.makeText(context,"hiiii",Toast.LENGTH_LONG).show();
                   // mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }


    @Override
    public void onItemDismiss(int position)
    {
        mMessageList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(mMessageList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount()
    {
        return mMessageList.size();
    }

}
/*
public class CustomerListAdapter extends
        RecyclerView.Adapter<CustomerListAdapter.ImageViewHolder>
        implements ItemTouchHelperAdapter
    {
    Context context;
    public List<Contact> mMessageList;
    public OnStartDragListener mDragStartListener;
    public OnCustomerListChangedListener mListChangedListener;

    public CustomerListAdapter(List<Contact> mMessageList, Context context,
                               OnStartDragListener dragListener,
                               OnCustomerListChangedListener listChangedListener){
        this.mMessageList = mMessageList;
        this.context = context;
        mDragStartListener = dragListener;
        mListChangedListener = listChangedListener;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(rowView);
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder
    {
        public ImageView messageImage;
        public ImageViewHolder(View itemView)
        {
            super(itemView);
            messageImage = (ImageView) itemView.findViewById(R.id.imageView1);
        }

        @Override
        public void onItemSelected()
        {

        }

        @Override
        public void onItemClear()
        {
            itemView.setBackgroundColor(0);
        }
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position)
    {

        Contact c = mMessageList.get(position);
        holder.messageImage.setImageURI(Uri.parse(c.getImage_url()));

        holder.messageImage.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
                {
                    try{
                        mDragStartListener.onStartDrag(holder);
                    }
                    catch (final NullPointerException ex)
                    {

                    }

                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(mMessageList, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mMessageList);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position)
    {

    }
}
*/
