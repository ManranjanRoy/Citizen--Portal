package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.DocumentListResponse;
import com.manoranjan.citizenportal.model.FileListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DocumentsListForform4Adaptor extends RecyclerView.Adapter<DocumentsListForform4Adaptor.ImageViewHolder> {
    private Context mContext;
    private List<FileListModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {

        void onShowClick(int position);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public DocumentsListForform4Adaptor(Context mContext, List<FileListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_duments, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final FileListModel uploadCurrent = mUploads.get(position);
        holder.title.setText(uploadCurrent.getName());
        Picasso.with(mContext)
                .load(uploadCurrent.getFile())
                .noFade()
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        Button show;

        public ImageViewHolder(View itemView, final OnitemClickListner listner) {
            super(itemView);
            title = itemView.findViewById(R.id.documenttitle);
            img = itemView.findViewById(R.id.img1);
            show=itemView.findViewById(R.id.showimg);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onShowClick(position);
                        }
                    }
                }
            });

        }


    }


}