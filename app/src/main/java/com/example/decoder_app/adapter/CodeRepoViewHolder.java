package com.example.decoder_app.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decoder_app.databinding.CodeFileItemLayoutBinding;
import com.example.decoder_app.model.CodeFiles;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class CodeRepoViewHolder extends RecyclerView.ViewHolder {

    private CodeFileItemLayoutBinding binding;

    public CodeRepoViewHolder(@NonNull CodeFileItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bind(CodeFiles codeFiles) {
        if (codeFiles != null) {
            binding.setCodeFile(codeFiles);
            binding.executePendingBindings();
        }
    }
}
