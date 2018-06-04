
package com.example.ssaloni.navigationmvvm.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.Util.Contact;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>
{
    Context context;
    private List<Contact> mMessageList;

    public ImageAdapter(List<Contact> mMessageList,Context context)
    {
        this.mMessageList = mMessageList;
        this.context=context;
    }


    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item ,parent, false);

        return new ImageViewHolder(v);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView messageImage;
        public ImageViewHolder(View itemView)
        {
            super(itemView);
            messageImage = (ImageView) itemView.findViewById(R.id.imageView1);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder viewHolder, int i)
    {
        Contact c = mMessageList.get(i);
        viewHolder.messageImage.setImageURI(Uri.parse(c.getImage_url()));
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
