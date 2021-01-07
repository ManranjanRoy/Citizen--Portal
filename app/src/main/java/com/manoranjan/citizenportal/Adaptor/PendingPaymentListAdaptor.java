package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.PendingPaymentModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PendingPaymentListAdaptor extends RecyclerView.Adapter<PendingPaymentListAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<PendingPaymentModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {

        void onPayClick(int position);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public PendingPaymentListAdaptor(Context mContext, List<PendingPaymentModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_pending_payment, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final PendingPaymentModel uploadCurrent = mUploads.get(position);

        holder.formno.setText(uploadCurrent.getFormNo());
        holder.appliedon.setText(uploadCurrent.getAppliedOn());
        holder.org_name.setText(uploadCurrent.getNameOrg());
        holder.wardno.setText(uploadCurrent.getWardNo());

       /* holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   *//*Intent i=new Intent(mContext, DoctorlistActivity.class);
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
        TextView formno, appliedon, org_name, wardno;
        Button pay;

        public ImageViewHolder(View itemView, final OnitemClickListner listner) {
            super(itemView);
            formno = itemView.findViewById(R.id.formno);
            appliedon = itemView.findViewById(R.id.appliedon);
            org_name = itemView.findViewById(R.id.orgname);
            wardno = itemView.findViewById(R.id.wardno);
            pay = itemView.findViewById(R.id.paybtn);

            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onPayClick(position);
                        }
                    }
                }
            });
        }


    }


}