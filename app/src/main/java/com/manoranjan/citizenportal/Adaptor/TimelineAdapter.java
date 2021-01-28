package com.manoranjan.citizenportal.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manoranjan.citizenportal.R;
import com.manoranjan.citizenportal.Response.SingleFollowupGraphResponse;
import com.manoranjan.citizenportal.model.ListItem;
import com.repsly.library.timelineview.LineType;
import com.repsly.library.timelineview.TimelineView;

import java.util.List;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final int orientation=LinearLayoutManager.VERTICAL;
    private final List<SingleFollowupGraphResponse> items;
    Context mcontext;

    public TimelineAdapter(Context context, List<SingleFollowupGraphResponse> items) {
        this.mcontext = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (orientation == LinearLayoutManager.VERTICAL) {
            return R.layout.item_vertical;
        } else {
            return R.layout.item_horizontal;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SingleFollowupGraphResponse mUploads=items.get(position);
        holder.tvName.setText(mUploads.getFollowupByUserType());
        holder.tvAddress.setText(mUploads.getRemarks());
        holder.timelineView.setLineType(getLineType(position));
        holder.timelineView.setNumber(position);



        if (position <items.size()-1) {
            holder.timelineView.setDrawable(AppCompatResources
                    .getDrawable(holder.timelineView.getContext(),
                            R.drawable.ic_checked));
        } else {
            holder.timelineView.setDrawable(null);
        }

        // Make first and last markers stroked, others filled
        if (position == 0 || position + 1 == getItemCount()) {
            holder.timelineView.setFillMarker(false);
        } else {
            holder.timelineView.setFillMarker(true);
        }
        // Set every third item active
        if (position!=0 &&position<items.size()-1) {
            holder.timelineView.setActive(true);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private LineType getLineType(int position) {
        if (getItemCount() == 1) {
            return LineType.ONLYONE;

        } else {
            if (position == 0) {
                return LineType.BEGIN;

            } else if (position == getItemCount() - 1) {
                return LineType.END;

            } else {
                return LineType.NORMAL;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TimelineView timelineView;
        TextView tvName;
        TextView tvAddress;

        ViewHolder(View view) {
            super(view);
            timelineView = (TimelineView) view.findViewById(R.id.timeline);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvAddress = (TextView) view.findViewById(R.id.tv_address);
        }
    }

}