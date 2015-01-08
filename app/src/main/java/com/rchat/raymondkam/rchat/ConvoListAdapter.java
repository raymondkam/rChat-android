package com.rchat.raymondkam.rchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RaymondKam on 1/8/2015.
 */
public class ConvoListAdapter extends RecyclerView.Adapter<ConvoListAdapter.ViewHolder> {

    private static final String TAG = "ConvoListAdapter";
    private ArrayList<String> convoListData = new ArrayList();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RelativeLayout convoRow;
        public ImageView convoImage;
        public TextView convoName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            convoImage = (ImageView) itemView.findViewById(R.id.convoRowImage);
            convoName = (TextView) itemView.findViewById(R.id.convoRowName);
        }

        @Override
        public void onClick(View v) {
            Log.e(TAG, "Click position: " + getPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_convo, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.convoName.setText(convoListData.get(i));
    }

    public void addItem(String newItemName) {
        this.convoListData.add(newItemName);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return convoListData.size();
    }
}
