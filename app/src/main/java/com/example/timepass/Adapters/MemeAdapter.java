package com.example.timepass.Adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timepass.Models.ApiModel;
import com.example.timepass.Models.ApiModel;
import com.example.timepass.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.viewHolder> {

    Context context;
    List<ApiModel> memeList;

    public MemeAdapter(Context context, List<ApiModel> memeList) {
        this.context = context;
        this.memeList = memeList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_meme_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ApiModel model = memeList.get(position);

        Picasso.get().load(model.getUrl()).placeholder(R.drawable.placeholder_dark).into(holder.memeImg);

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
//                intent.setType("image/jpeg");
//                Uri pictureUri = Uri.parse(model.getUrl());
//                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.putExtra(Intent.EXTRA_TEXT , "Check out his Meme, very funny: \n"+model.getUrl());
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(Intent.createChooser(intent, "Share With"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return memeList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView memeImg;
        TextView share;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            memeImg = itemView.findViewById(R.id.memeImg);
            share = itemView.findViewById(R.id.share);
        }
    }
}
