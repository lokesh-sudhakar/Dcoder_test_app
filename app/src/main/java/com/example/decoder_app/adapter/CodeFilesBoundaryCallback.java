package com.example.decoder_app.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.example.decoder_app.model.CodeFiles;
import com.example.decoder_app.model.CodeResponse;
import com.example.decoder_app.repository.DcoderRepository;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class CodeFilesBoundaryCallback extends PagedList.BoundaryCallback<CodeFiles> {

    private static final String TAG = "CodeFilesBoundaryCallba";
    private int page = 1;
    private int maxPages = 0;
    private DcoderRepository repository;
    private CompositeDisposable compositeDisposable;

    @Inject
    public CodeFilesBoundaryCallback(DcoderRepository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        fetchCodeFiles();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull CodeFiles itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        fetchCodeFiles();
    }

    private void fetchCodeFiles() {
        if (maxPages!=0 && page>maxPages) {
            return;
        }
        compositeDisposable.add(repository.getCodeDataFromRemote(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCodeFilesResponse, error -> Log.d(TAG, "fetchCodeFiles: onError")));
    }

    private void onCodeFilesResponse(CodeResponse response) {
        if (response.getData().isEmpty()) {
            return;
        }
        if (page == 1) {
            maxPages = response.getPages();
            clearDbAndSaveNewCodeFile(response);
        } else {
            insertCodeFilesInDb(response);
        }
        page += 1;
    }

    private void insertCodeFilesInDb(CodeResponse response) {
        repository.insertCodeFiles(response.getData()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }

    private void clearDbAndSaveNewCodeFile(CodeResponse response) {
        repository.clearCodeFilesFromDb().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        insertCodeFilesInDb(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }

    public void onClear() {
        compositeDisposable.clear();
    }
}
