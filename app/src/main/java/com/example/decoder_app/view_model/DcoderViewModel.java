package com.example.decoder_app.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.decoder_app.DcoderApplication;
import com.example.decoder_app.adapter.CodeFilesBoundaryCallback;
import com.example.decoder_app.filter.FilterConditions;
import com.example.decoder_app.filter.FilterType;
import com.example.decoder_app.model.CodeFiles;
import com.example.decoder_app.repository.DcoderRepository;
import com.example.decoder_app.utils.BasicUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class DcoderViewModel extends AndroidViewModel {

    private CodeFilesBoundaryCallback boundaryCallback;
    private MutableLiveData<FilterConditions> filterConditionLiveData = new MutableLiveData<>();
    private Executor executor;
    private LiveData<PagedList<CodeFiles>> codeFilesPagedList;
    private boolean isFolderSelected = false;
    private String queryText="";
    private String selectedFilter = "";
    @Inject
    DcoderRepository repository;

    public DcoderViewModel(@NonNull Application application) {
        super(application);
        DcoderApplication.getInstance().getAppComponent().inject(this);
        boundaryCallback = DcoderApplication.getInstance().getAppComponent()
                .getCodeFilesBoundaryCallback();
        initPagedList();
    }

    private void initPagedList() {
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(10);
        codeFilesPagedList = Transformations.switchMap(filterConditionLiveData, conditions ->
                (new LivePagedListBuilder<>(repository.getCodeDataFromDb(conditions), config))
                        .setBoundaryCallback(boundaryCallback)
                        .setFetchExecutor(executor)
                        .build()
        );
    }

    public void performSearch(CharSequence query) {
        setQueryText(query.toString());
        FilterConditions conditions;
        if (isFolderSelected()) {
            conditions = new FilterConditions.Builder(FilterType.Folder)
                    .setQuery(query.toString())
                    .setProjectLanguage(BasicUtils.getFolderLanguageSelected(getSelectedFilter()))
                    .build();
        } else {
            conditions = new FilterConditions.Builder(FilterType.File)
                    .setQuery(query.toString())
                    .setFileLanguage(BasicUtils.getFileLanguageSelected(getSelectedFilter()))
                    .build();
        }
        getFilterConditionLiveData().setValue(conditions);
    }

    public MutableLiveData<FilterConditions> getFilterConditionLiveData() {
        return filterConditionLiveData;
    }

    public LiveData<PagedList<CodeFiles>> getCodeFilesPagedList() {
        return codeFilesPagedList;
    }

    @Override
    protected void onCleared() {
        boundaryCallback.onClear();
        super.onCleared();
    }

    public boolean isFolderSelected() {
        return isFolderSelected;
    }

    public void setFolderSelected(boolean folderSelected) {
        isFolderSelected = folderSelected;
    }

    public String getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedFilter(String selectedFilter) {
        this.selectedFilter = selectedFilter;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }
}
