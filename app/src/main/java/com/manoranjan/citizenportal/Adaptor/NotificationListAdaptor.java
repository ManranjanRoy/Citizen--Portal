package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.model.NotificatonModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class NotificationListAdaptor extends RecyclerView.Adapter<NotificationListAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<NotificatonModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {
        void onForwardClick(int position);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public NotificationListAdaptor(Context mContext, List<NotificatonModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_notification, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final NotificatonModel uploadCurrent = mUploads.get(position);

        holder.title.setText(uploadCurrent.getFormNo());
        holder.desc.setText(uploadCurrent.getRemarks());
        holder.date.setText(uploadCurrent.getFollowup_Date());
        if (uploadCurrent.getFollowup_By_User_ID().equals("0")){
            holder.forward.setVisibility(View.GONE);
        }else{
            holder.forward.setVisibility(View.VISIBLE);
        }

        /* holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent i=new Intent(mContext, DoctorlistActivity.class);
                    i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.putExtra("url",uploadCurrent.getId());
                    //Toast.makeText(mContext,uploadCurrent.getId(),Toast.LENGTH_SHORT).show();
                    StaticData.hospital_id=uploadCurrent.getId();
                    mContext.startActivity(i);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, date;
        ImageView icon;
        LinearLayout forward;

        public ImageViewHolder(View itemView, final OnitemClickListner listner) {
            super(itemView);
            title = itemView.findViewById(R.id.ntitle);
            desc = itemView.findViewById(R.id.ndesc);
            date = itemView.findViewById(R.id.date);
            icon = itemView.findViewById(R.id.icon);
            forward=itemView.findViewById(R.id.forward);
            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onForwardClick(position);
                        }
                    }
                }
            });
        }
    }
}