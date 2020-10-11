package com.example.decoder_app.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decoder_app.R;
import com.example.decoder_app.databinding.CodeFileItemLayoutBinding;
import com.example.decoder_app.model.CodeFiles;
import com.example.decoder_app.utils.BasicUtils;

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


    public void bind(CodeFiles codeFiles){
        if (codeFiles!=null) {
            binding.title.setText(BasicUtils.toCamelCase(codeFiles.getFile()));
            binding.subTitle.setText(BasicUtils.toCamelCase(codeFiles.getUsername()));
            binding.starText.setText(codeFiles.getStars().getNumber().toString());
            if (codeFiles.getIsProject()) {
                binding.icon.setImageDrawable(binding.icon.getContext().getResources().getDrawable(R.drawable.ic_folder_white_24dp));
            } else {
                binding.icon.setImageDrawable(binding.icon.getContext().getResources().getDrawable(R.drawable.ic_file_white_24dp));
            }
        }
    }
}
