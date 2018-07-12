package com.example.ssaloni.navigationmvvm.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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
import com.example.ssaloni.navigationmvvm.databinding.ImageItemBinding;
import com.example.ssaloni.navigationmvvm.viewModel.GridViewModel;
import com.example.ssaloni.navigationmvvm.viewModel.RecyclerViewAdapterViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter, RecyclerViewAdapterViewModel.DataListener {
    public List<Contact> mMessageList;

    public final OnStartDragListener mDragStartListener;
    public Context context;
    public int mShortAnimationDurationEffect;
    private Animator mCurrentAnimatorEffect;
    RecyclerViewAdapterViewModel recyclerViewAdapterViewModel;
    ImageItemBinding imageItemBinding;
    DataListener dataListener;
    int selectedPos = 0;

    public CustomerListAdapter(List<Contact> mMessageList, OnStartDragListener dragStartListener, Context context) {
        this.context = context;
        mDragStartListener = dragStartListener;
        this.mMessageList = mMessageList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        public final ImageView messageImage;
        public final ImageView expandedImageView;
        int selected;


        public ItemViewHolder(View itemView) {
            super(itemView);
            messageImage = (ImageView) itemView.findViewById(R.id.imageView1);
            expandedImageView = (ImageView) itemView.findViewById(R.id.expandedimage);
        }

        @Override
        public void onItemSelected() {

            itemView.setBackgroundColor(Color.RED);
        }

        @Override
        public void onItemClear() {

            itemView.setBackgroundColor(0);
        }



    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position)
    {
        recyclerViewAdapterViewModel = new RecyclerViewAdapterViewModel(context);
        Contact c = mMessageList.get(position);
        holder.messageImage.setImageURI(Uri.parse(c.getImage_url()));

        holder.messageImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mMessageList.get(position);
                int selectPos=position;
                if(position==selectPos) {

                    holder.messageImage.setBackgroundColor(Color.BLACK);
                }
                else {

                    holder.messageImage.setBackgroundColor(Color.BLUE);
                }

            }
        });



              /* if(position == selectedPosition)
               {
                   holder.messageImage.setBackgroundColor(Color.RED);
               }
               else
               {
                   holder.messageImage.setBackgroundColor(Color.BLACK);
               }*/

       /* holder.messageImage.setOnClickListener(new View.OnClickListener()
        {
            private boolean isactive = false;
            @Override
            public void onClick(View view)
            {
                if(!isactive)
                {
                    holder.messageImage.setBackgroundColor(Color.RED);
                    Toast.makeText(context, "HIIIII", Toast.LENGTH_LONG).show();
                    isactive = true;
                }
                else
                {
                    holder.messageImage.setBackgroundColor(Color.BLUE);
                    Toast.makeText(context, "BYEEEE", Toast.LENGTH_LONG).show();
                    isactive=false;
                }
            }
        });*/
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
    public int getItemCount() {

        return mMessageList.size();
    }

    public interface DataListener {

    }

}

