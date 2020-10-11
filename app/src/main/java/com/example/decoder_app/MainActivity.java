package com.example.decoder_app;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decoder_app.adapter.CodeRepoListAdapter;
import com.example.decoder_app.databinding.ActivityMainBinding;
import com.example.decoder_app.filter.FilterConditions;
import com.example.decoder_app.filter.FilterType;
import com.example.decoder_app.utils.BasicUtils;
import com.example.decoder_app.view_model.DcoderViewModel;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private final long DEBOUNCE_TIME_IN_MS = 100;
    private DcoderViewModel viewModel;
    private ActivityMainBinding binding;
    private CodeRepoListAdapter adapter;
    private CompositeDisposable compositeDisposable;
    private boolean isFolderSelected = false;
    private String selected = "";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(DcoderViewModel.class);
        compositeDisposable = new CompositeDisposable();
        initViews();
        textChangeListener();
    }

    private void textChangeListener() {
        compositeDisposable.add(RxTextView.textChanges(binding.searchEditView)
                .debounce(DEBOUNCE_TIME_IN_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::performSearch));
    }

    private void initViews() {
        initRecyclerView();
        viewModel.getCodeFilesPagedList().observe(this, codeFilesPagedList -> {
            adapter.submitList(codeFilesPagedList);
            adapter.notifyDataSetChanged();
        });
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                removeFocusFromSearch();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    binding.codeSwitch.setVisibility(View.GONE);
                } else {
                    binding.codeSwitch.setVisibility(View.VISIBLE);
                }

            }
        });
        setSwitchClickListener();
        switchFolderSelection();
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> languageFileAdapter;
        if (isFolderSelected) {
            languageFileAdapter = ArrayAdapter.createFromResource(this,
                    R.array.ProjectLanguage, android.R.layout.simple_spinner_item);
        } else {
            languageFileAdapter = ArrayAdapter.createFromResource(this,
                    R.array.FileLanguage, android.R.layout.simple_spinner_item);
        }
        languageFileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.filter.setAdapter(languageFileAdapter);
        binding.filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected = (String) adapterView.getItemAtPosition(position);
                if (isFolderSelected) {
                    binding.filterText.setText(selected);
                    binding.searchEditView.setText("");
                } else {
                    binding.filterText.setText(selected);
                    binding.searchEditView.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSwitchClickListener() {
        binding.fileRadio.setOnClickListener(view -> {
            if (isFolderSelected) {
                switchFolderSelection();
            }
        });
        binding.folderRadio.setOnClickListener(view -> {
            if (!isFolderSelected) {
                switchFolderSelection();
            }
        });
    }

    public void switchFolderSelection() {
        isFolderSelected=!isFolderSelected;
        selected = "";
        if (isFolderSelected) {
            viewModel.getFilterConditionLiveData().setValue(new FilterConditions.Builder(FilterType.Folder).build());
            binding.folderRadio.setImageDrawable(getResources().getDrawable(R.drawable.ic_folder_blue_24dp));
            binding.fileRadio.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_white_24dp));
            binding.folderText.setTextColor(getResources().getColor(R.color.blue));
            binding.filterText.setTextColor(getResources().getColor(R.color.white));
        } else {
            viewModel.getFilterConditionLiveData().setValue(new FilterConditions.Builder(FilterType.File).build());
            binding.fileRadio.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_blue_24dp));
            binding.folderRadio.setImageDrawable(getResources().getDrawable(R.drawable.ic_folder_white_24dp));
            binding.folderText.setTextColor(getResources().getColor(R.color.white));
            binding.filterText.setTextColor(getResources().getColor(R.color.blue));
        }
        initSpinner();
    }

    private void performSearch(CharSequence query) {
        FilterConditions conditions;
        if (isFolderSelected) {
             conditions = new FilterConditions.Builder(FilterType.Folder)
                    .setQuery(query.toString())
                     .setProjectLanguage(BasicUtils.getFolderLanguageSelected(selected))
                    .build();
        } else {
             conditions = new FilterConditions.Builder(FilterType.File)
                    .setQuery(query.toString())
                     .setFileLanguage(BasicUtils.getFileLanguageSelected(selected))
                     .build();
        }
        viewModel.getFilterConditionLiveData().setValue(conditions);
    }

    private void removeFocusFromSearch() {
        binding.searchEditView.clearFocus();
        BasicUtils.hideKeyboard(this,binding.searchEditView);
    }


    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new CodeRepoListAdapter();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
    }

}
