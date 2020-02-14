package com.example.dashboard_experiment;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


//import com.sendbird.android.sample.R;
//import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;
import java.util.List;

class DashboardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> PostList;

    private Context mContext;

    private OnItemLongClickListener mItemLongClickListener;
    private OnItemClickListener mItemClickListener;

    interface OnItemClickListener {
        void onItemClick(String str);
    }

    interface OnItemLongClickListener {
        void onItemLongClick(String str);
    }

DashboardListAdapter(Context context) {

    mContext = context;
    PostList =new ArrayList<>();
}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ChannelHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ChannelHolder) holder).bind(mContext, position, PostList.get(position), mItemClickListener, mItemLongClickListener);
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

    void setGroupChannelList(List<String> channelList) {
        PostList = channelList;
        notifyDataSetChanged();
    }

    void addLast(String str){
        PostList.add(str);
        notifyItemInserted(PostList.size()-1);
    }

    public void load(){
        try{
            PostList.clear();
            PostList.add("Sourish");
            PostList.add("Hello");
            PostList.add("Hello");
            PostList.add("Hello");
            PostList.add("Hello");
            Log.d("LIST:",PostList.toString());
            notifyDataSetChanged();
        } catch(Exception e){
            //nothing to load
        }
    }

    /**
     * A ViewHolder that contains UI to display information about a GroupChannel.
     */
    private class ChannelHolder extends RecyclerView.ViewHolder {
        TextView post;

        ChannelHolder(View itemView){
            super(itemView);
            post = (TextView)itemView.findViewById(R.id.post);
        }
        void bind(final Context context, int postition,final String str, @Nullable final OnItemClickListener clickListener, @Nullable final OnItemLongClickListener longClickListener){
            post.setText(str);

            // Set an OnClickListener to this item.
            if (clickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(str);
                    }
                });
            }

            // Set an OnLongClickListener to this item.
            if (longClickListener != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        longClickListener.onItemLongClick(str);

                        // return true if the callback consumed the long click
                        return true;
                    }
                });
            }
        }

    }

    void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListener = listener;
    }

    void setOnItemLongClickLsitener(OnItemLongClickListener listener){
        mItemLongClickListener = listener;
    }

}
