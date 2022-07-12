package com.example.retrofitassignment.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitassignment.databinding.MarspropertyItemBinding;
import com.example.retrofitassignment.network.data.model.MarsProperty;

public class MarsPropertyAdapter extends ListAdapter<MarsProperty, MarsPropertyAdapter.MarsPropertyViewHolder> {

    private final OnClickItemListener mOnClickItemListener;

    public MarsPropertyAdapter(@NonNull DiffUtil.ItemCallback<MarsProperty> diffCallback, OnClickItemListener onClickItemListener) {
        super(diffCallback);
        mOnClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public MarsPropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MarspropertyItemBinding binding =
                MarspropertyItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MarsPropertyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarsPropertyViewHolder holder, int position) {
        MarsProperty marsProperty = getItem(position);
        if (marsProperty != null) {
            holder.mBinding.setMarsProperty(marsProperty);
            holder.mBinding.root.setOnClickListener(v -> mOnClickItemListener.onClickItem(marsProperty));
            holder.mBinding.executePendingBindings();
        }
    }

    public static class MarsPropertyViewHolder extends RecyclerView.ViewHolder {
        private final MarspropertyItemBinding mBinding;

        public MarsPropertyViewHolder(@NonNull MarspropertyItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface OnClickItemListener {
        void onClickItem(MarsProperty marsProperty);
    }
}
