package com.example.imagepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MultiImageAdapter extends RecyclerView.Adapter<MultiImageAdapter.ViewHolder>{
    private  ArrayList<Uri> mData = null;
    private Context mContext = null;
    public String mlabel = null;
    MultiImageAdapter(ArrayList<Uri> list, Context context){
        mData = list;
        mContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        ViewHolder(View itemView){
            super(itemView);

            image = itemView.findViewById(R.id.image);

        }
    }
    @Override
    public MultiImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pet,parent,false);
        MultiImageAdapter.ViewHolder vh = new MultiImageAdapter.ViewHolder(view);
        return vh;

    }
    @Override
    public void onBindViewHolder(MultiImageAdapter.ViewHolder holder, int position){
        Uri image_uri = mData.get(position) ;

        Glide.with(mContext)
                .load(image_uri)
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
    public void clear(){
        mData.clear();
    }
}
