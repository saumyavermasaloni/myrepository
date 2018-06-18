
package com.example.ssaloni.navigationmvvm.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ssaloni.navigationmvvm.Helper.ItemTouchHelperAdapter;
import com.example.ssaloni.navigationmvvm.R;
import com.example.ssaloni.navigationmvvm.Util.Contact;

import java.util.Collections;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements ItemTouchHelperAdapter
{
    Context context;
    private List<Contact> mMessageList;

    public ImageAdapter(List<Contact> mMessageList, Context context)
    {
        this.mMessageList = mMessageList;
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
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
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        Contact c = mMessageList.get(position);
        holder.messageImage.setImageURI(Uri.parse(c.getImage_url()));
    }

    @Override
    public int getItemCount()
    {
        return mMessageList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(mMessageList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    @Override
    public void onItemDismiss(int position)
    {

    }
}
 /* public class ImageAdapter extends BaseAdapter implements ItemTouchHelperAdapter
 {

    Context context;
    private List<Contact> mMessageList;
    LayoutInflater layoutInflater;
    View view;
    ImageView messageImage;

    public ImageAdapter(Context context, List<Contact> mMessageList)
    {
        this.context = context;
        this.mMessageList = mMessageList;
    }

    @Override
    public int getCount()
    {
        return mMessageList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup)
    {

        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertview==null)
        {
            view = new View(context);
            view=layoutInflater.inflate(R.layout.image_item,null);
            ImageView messageImage=(ImageView) view.findViewById(R.id.imageView1);
            Contact c = mMessageList.get(i);
            messageImage.setImageURI(Uri.parse(c.getImage_url()));
            Log.i("info", String.valueOf(messageImage));
        }
        return view;
    }

     @Override
     public boolean onItemMove(int fromPosition, int toPosition)
     {
         Collections.swap(mMessageList, fromPosition, toPosition);
         notifyItemMoved(fromPosition, toPosition);
         return true;
     }

     @Override
     public void onItemMoved()
     {

     }

     @Override
     public void onItemDismiss(int position)
     {

     }
 }

*/
/*
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
*/


