package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class OwnerofTradeAdaptor extends RecyclerView.Adapter<OwnerofTradeAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<TypeofBusinessListModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {

        void onDeleteClick(int position);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public OwnerofTradeAdaptor(Context mContext, List<TypeofBusinessListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_ownerlist, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final TypeofBusinessListModel uploadCurrent = mUploads.get(position);

        holder.name.setText(uploadCurrent.getName());
        holder.fathername.setText(uploadCurrent.getFathername());
        holder.contact.setText(uploadCurrent.getAddress());
        holder.address.setText(uploadCurrent.getAddress());

       /* holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   */
       /*Intent i=new Intent(mContext, DoctorlistActivity.class);
                    i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.putExtra("url",uploadCurrent.getId());
                    //Toast.makeText(mContext,uploadCurrent.getId(),Toast.LENGTH_SHORT).show();
                    StaticData.hospital_id=uploadCurrent.getId();
                    mContext.startActivity(i);*//*
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView name, fathername, contact, address, pancard;
        ImageView delete;

        public ImageViewHolder(View itemView, final OnitemClickListner listner) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            fathername = itemView.findViewById(R.id.fathername);
            contact = itemView.findViewById(R.id.contactno);
            address = itemView.findViewById(R.id.address);
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