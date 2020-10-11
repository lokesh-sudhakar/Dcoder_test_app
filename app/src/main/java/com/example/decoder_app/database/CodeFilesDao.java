package com.example.decoder_app.database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.decoder_app.model.CodeFiles;

import java.util.List;

import io.reactivex.Completable;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */
@Dao
public interface CodeFilesDao {

    @Insert
    Completable insertCodeFiles(List<CodeFiles> codeFiles);

    @Query("Select * From code_files_table where isProject is 0 ")
    DataSource.Factory<Integer, CodeFiles> getAllCodeFiles();

    @Query("Select * From code_files_table where isProject is 1 ")
    DataSource.Factory<Integer, CodeFiles> getAllCodeProject();

    @Query("DELETE FROM code_files_table")
    Completable delete();

    @Query("Select * From code_files_table where file like '%'||:query||'%' AND isProject is 1")
    DataSource.Factory<Integer, CodeFiles> getCodeProjectWithQueryFilter(String query);

    @Query("Select * From code_files_table where file like '%'||:query||'%' AND isProject is 0")
    DataSource.Factory<Integer, CodeFiles> getCodeFileWithQueryFilter(String query);

    @Query("Select * From code_files_table where languageId is :lang AND isProject is 1")
    DataSource.Factory<Integer, CodeFiles> getCodeProjectWithLanguageFilter(int lang);

    @Query("Select * From code_files_table where languageId is :lang AND isProject is 0")
    DataSource.Factory<Integer, CodeFiles> getCodeFileWithLanguageFilter(int lang);

    @Query("Select * From code_files_table where file like '%'||:query||'%' AND languageId is :lang AND isProject is 1")
    DataSource.Factory<Integer, CodeFiles> getCodeProjectWithLanguageAndQueryFilter(String query,int lang);

    @Query("Select * From code_files_table where file like '%'||:query||'%' AND languageId is :lang AND isProject is 0")
    DataSource.Factory<Integer, CodeFiles> getCodeFileWithLanguageAndQueryFilter(String query, int lang);
}
