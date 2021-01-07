package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.MytradeLicenseModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyTadeLicenseListAdaptor extends RecyclerView.Adapter<MyTadeLicenseListAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<MytradeLicenseModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {

        void onViewClick(int position);
        void onViewLicenseClick(int position);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public MyTadeLicenseListAdaptor(Context mContext, List<MytradeLicenseModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_trade_license, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final MytradeLicenseModel uploadCurrent = mUploads.get(position);

        holder.formno.setText(uploadCurrent.getFormNo());
        holder.appliedon.setText(uploadCurrent.getAppliedOn());
        holder.org_name.setText(uploadCurrent.getNameOrg());
        holder.wardno.setText(uploadCurrent.getWardNo());
        holder.status.setText(uploadCurrent.getStatusName());
        holder.fl_year.setText(uploadCurrent.getFinYearName());
        if (uploadCurrent.getTLType().equals("N")){
            holder.tl_type.setText("New");
        }else{
            holder.tl_type.setText("Renew");
        }
        //ISSUED
        if (uploadCurrent.getStatusName().equals("ISSUED")){
            holder.viewlicense.setVisibility(View.VISIBLE);
        }else{
            holder.viewlicense.setVisibility(View.GONE);
        }

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
        TextView formno, appliedon, org_name, wardno,tl_type,status,fl_year;
        Button view,viewlicense;

        public ImageViewHolder(View itemView, final OnitemClickListner listner) {
            super(itemView);
            formno = itemView.findViewById(R.id.formno);
            appliedon = itemView.findViewById(R.id.appliedon);
            org_name = itemView.findViewById(R.id.orgname);
            wardno = itemView.findViewById(R.id.wardno);
            view = itemView.findViewById(R.id.view);
            viewlicense = itemView.findViewById(R.id.viewlicence);
            tl_type = itemView.findViewById(R.id.tltype);
            fl_year = itemView.findViewById(R.id.flyear);
            status = itemView.findViewById(R.id.status);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onViewClick(position);
                        }
                    }
                }
            });
            viewlicense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onViewLicenseClick(position);
                        }
                    }
                }
            });
        }


    }


}