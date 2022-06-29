package com.tuita.filedownloadt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DownAdapter extends RecyclerView.Adapter<DownAdapter.DownViewHolder> {
    private final ArrayList<Bean> fileList;
    private final Context context;
    private AdapterItemClick adapterItemClick;

    public void setAdapterItemClick(AdapterItemClick adapterItemClick) {
        this.adapterItemClick = adapterItemClick;
    }

    public DownAdapter(ArrayList<Bean> fileList, Context context) {
        this.fileList = fileList;
        this.context = context;
    }

    @NonNull
    @Override
    public DownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DownViewHolder(LayoutInflater.from(context).inflate(R.layout.item_download, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DownViewHolder holder, int position) {
        Bean bean = fileList.get(position);
        holder.mItemName.setText(bean.getName());
        holder.mItemKb.setText(bean.getKbProcess());
        holder.mItemProcess.setMax(bean.getMaxProcess());
        holder.mItemProcess.setProgress(bean.getProcess());
        Glide.with(context).load(bean.getSrc()).into(holder.mItemSrc);
        holder.mItemSrc.setOnClickListener(v ->
                adapterItemClick.onItemClickListener(DownAdapter.this, v, holder.getAdapterPosition())
        );
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class DownViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mItemSrc;
        private final TextView mItemName;
        private final TextView mItemKb;
        private final ProgressBar mItemProcess;

        public DownViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemSrc = itemView.findViewById(R.id.item_src);
            mItemName = itemView.findViewById(R.id.item_name);
            mItemKb = itemView.findViewById(R.id.item_kb);
            mItemProcess = itemView.findViewById(R.id.item_process);
        }
    }

    public interface AdapterItemClick {
        void onItemClickListener(DownAdapter downAdapter, View view, int position);

    }
}
