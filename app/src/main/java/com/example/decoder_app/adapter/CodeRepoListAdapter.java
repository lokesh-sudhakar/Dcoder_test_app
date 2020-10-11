package com.example.decoder_app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.decoder_app.R;
import com.example.decoder_app.databinding.CodeFileItemLayoutBinding;
import com.example.decoder_app.model.CodeFiles;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class CodeRepoListAdapter extends PagedListAdapter<CodeFiles, CodeRepoViewHolder> {


    public CodeRepoListAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<CodeFiles> diffCallback = new DiffUtil.ItemCallback<CodeFiles>() {
        @Override
        public boolean areItemsTheSame(@NonNull CodeFiles oldItem, @NonNull CodeFiles newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CodeFiles oldItem, @NonNull CodeFiles newItem) {
            return true;
        }
    };

    @NonNull
    @Override
    public CodeRepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CodeFileItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.code_file_item_layout, parent, false);
        return new CodeRepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeRepoViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

}
