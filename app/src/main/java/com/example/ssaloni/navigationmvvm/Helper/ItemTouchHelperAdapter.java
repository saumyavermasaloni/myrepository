package com.example.ssaloni.navigationmvvm.Helper;

/**
 * Created by ssaloni on 6/12/2018.
 */

public interface ItemTouchHelperAdapter
{
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}
