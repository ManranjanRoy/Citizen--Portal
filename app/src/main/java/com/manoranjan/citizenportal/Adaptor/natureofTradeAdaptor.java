package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.TradeNatureModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class natureofTradeAdaptor extends RecyclerView.Adapter<natureofTradeAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<TradeNatureModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {

        void onDeleteClick(int position);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public natureofTradeAdaptor(Context mContext, List<TradeNatureModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.iten_schedule, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final TradeNatureModel uploadCurrent = mUploads.get(position);
        holder.textday.setText(uploadCurrent.getType());
        holder.texttime.setText(uploadCurrent.getName());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView textday, texttime;
        ImageView delete;

        public ImageViewHolder(View itemView, final OnitemClickListner listner) {
            super(itemView);
            textday = itemView.findViewById(R.id.textdate);
            texttime = itemView.findViewById(R.id.texttime);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onDeleteClick(position);
                        }
                    }
                }
            });
        }


    }


}