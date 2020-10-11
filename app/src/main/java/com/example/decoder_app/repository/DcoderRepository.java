package com.example.decoder_app.repository;

import android.app.Application;

import androidx.paging.DataSource;

import com.example.decoder_app.database.CodeFilesDao;
import com.example.decoder_app.database.CodeFilesDatabase;
import com.example.decoder_app.filter.FileLanguageFilter;
import com.example.decoder_app.filter.FilterConditions;
import com.example.decoder_app.filter.FilterType;
import com.example.decoder_app.model.CodeFiles;
import com.example.decoder_app.model.CodeResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class DcoderRepository {

    private Retrofit retrofit;
    private CodeFilesDao dao;

    @Inject
    public DcoderRepository(Application application, Retrofit retrofit) {
        this.retrofit = retrofit;
        CodeFilesDatabase database = CodeFilesDatabase.getDatabase(application);
        dao = database.codeFilesDao();
    }

    public Observable<CodeResponse> getCodeDataFromRemote(int page) {
        return retrofit.create(DcoderAPI.class).getData(page);
    }

    public Completable insertCodeFiles(List<CodeFiles> codeFiles) {
        return dao.insertCodeFiles(codeFiles);
    }

    public Completable clearCodeFilesFromDb() {
        return dao.delete();
    }

    public DataSource.Factory<Integer, CodeFiles> getCodeDataFromDb(FilterConditions conditions) {
        if (!conditions.getQuery().isEmpty() && conditions.getFilterType().equals(FilterType.File)
                && conditions.getFileLanguageFilter() != FileLanguageFilter.ALL) {
            return dao.getCodeFileWithLanguageAndQueryFilter(conditions.getQuery(), conditions.getFileLanguageFilter());
        } else if (conditions.getFilterType().equals(FilterType.File)
                && conditions.getFileLanguageFilter() != FileLanguageFilter.ALL) {
            return dao.getCodeFileWithLanguageFilter(conditions.getFileLanguageFilter());
        } else if (!conditions.getQuery().isEmpty() && conditions.getFilterType().equals(FilterType.File)
                && conditions.getFileLanguageFilter() == FileLanguageFilter.ALL) {
            return dao.getCodeFileWithQueryFilter(conditions.getQuery());
        } else if (conditions.getFilterType().equals(FilterType.File)) {
            return dao.getAllCodeFiles();
        } else if (!conditions.getQuery().isEmpty() && conditions.getFilterType().equals(FilterType.Folder)
                && conditions.getProjectLanguageFilter() != FileLanguageFilter.ALL) {
            return dao.getCodeProjectWithLanguageAndQueryFilter(conditions.getQuery(), conditions.getProjectLanguageFilter());
        } else if (conditions.getFilterType().equals(FilterType.Folder)
                && conditions.getProjectLanguageFilter() != FileLanguageFilter.ALL) {
            return dao.getCodeProjectWithLanguageFilter(conditions.getProjectLanguageFilter());
        } else if (!conditions.getQuery().isEmpty() && conditions.getFilterType().equals(FilterType.Folder)
                && conditions.getProjectLanguageFilter() == FileLanguageFilter.ALL) {
            return dao.getCodeProjectWithQueryFilter(conditions.getQuery());
        } else if (conditions.getFilterType().equals(FilterType.Folder)) {
            return dao.getAllCodeProject();
        } else {
            return dao.getAllCodeProject();
        }
    }
}
