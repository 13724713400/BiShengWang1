/*
package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;

*/
/**
 * Created by Administrator on 2015/12/12.
 *//*

public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoAdapter.MyViewHolder> {

    private Context context;
    private ItemOnclickTwo itemOnclick;
    private ArrayList<String> imglist;

    public void setImglist(ArrayList<String> imglist) {
        this.imglist = imglist;
    }

    public ArrayList<String> getImglist() {
        return imglist;
    }

    public void setItemOnclick(ItemOnclickTwo itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

   public interface ItemOnclickTwo{
        void itemOnclick();
    }

    public MyPhotoAdapter(Context context, ArrayList<String> imglist)
    {
        this.context = context;
        this.imglist = imglist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.myphoto_listview_item,null));
        return holder;
    }

    public void addData(String imgUrl)
    {
        imglist.add(1,imgUrl);
        notifyItemInserted(1);
        Log.i("www","size"+imglist.size());
    }

    public void removeData()
    {
        */
/*notifyItemRemoved();*//*

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
      //  (ImageView)holder.itemView.setI
        Glide.with(context)
                .load(imglist.get(position))
                .fitCenter()
                .into(holder.imageView);
      */
/*  Picasso.with(context)
                .load(imglist.get(position))

                .into(holder.imageView);
*//*


        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DbUtils.getInstance().deleteItem(imglist.get(position));
              itemOnclick.itemOnclick();

              return false;
            }
        });

        Log.i("www", "imglist" + imglist.get(position));
      //  holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imglist.get(position)));
    }

    @Override
    public int getItemCount() {
        return imglist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        public MyViewHolder(View view)
        {
            super(view);
             imageView = (ImageView) view.findViewById(R.id.myphoto_item_img);
        }
    }

}
*/
