package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoranjan.citizenportal.Api.StaticData;
import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.DocumentListResponse;
import com.manoranjan.citizenportal.model.DocTypeListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

//for populating dox types in screen
public class DocstypesListdataAdaptor extends RecyclerView.Adapter<DocstypesListdataAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<DocTypeListModel> mUploads;
    private OnitemClickListner mlistner;

    public interface OnitemClickListner {

        void onShowClick(int position,View itemView);
    }

    public void setonItemClickListner(OnitemClickListner listner) {
        mlistner = listner;

    }

    public DocstypesListdataAdaptor(Context mContext, List<DocTypeListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_doc_select, parent, false);
        return new ImageViewHolder(v, mlistner);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final DocTypeListModel uploadCurrent = mUploads.get(position);
        holder.title.setText(uploadCurrent.getDocumentName());

        if (StaticData.fileslist!=null&& StaticData.fileslist.size()>0){
            int a=0;
            for (int i=0;i<StaticData.fileslist.size();i++){
                if (uploadCurrent.getDocumentId()==StaticData.fileslist.get(0).getId()){
                    a=1;
                    Picasso.with(mContext)
                            .load(StaticData.fileslist.get(i).getFile())
                            .noFade()
                            .placeholder(R.drawable.ic_baseline_cloud_upload_24)
                            .into(holder.img);
                    break;
                }
            }
            if (a==0){
                Picasso.with(mContext)
                        .load(R.drawable.uploadimg)
                        .noFade()
                        .placeholder(R.drawable.uploadimg)
                        .into(holder.img);
            }
        }
        else{
            Picasso.with(mContext)
                    .load(R.drawable.uploadimg)
                    .noFade()
                    .placeholder(R.drawable.uploadimg)
                    .into(holder.img);
        }


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        Button show;

        public ImageViewHolder(final View itemView, final OnitemClickListner listner) {
            super(itemView);
            title = itemView.findViewById(R.id.docname);
            img = itemView.findViewById(R.id.img1);
           // show=itemView.findViewById(R.id.showimg);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onShowClick(position,itemView);
                        }
                    }
                }
            });

        }


    }


}