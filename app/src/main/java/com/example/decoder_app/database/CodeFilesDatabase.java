package com.example.decoder_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.decoder_app.model.CodeFiles;
import com.example.decoder_app.utils.DataTypeConverters;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */
@Database(entities = {CodeFiles.class},version = 2,exportSchema = false)
@TypeConverters(DataTypeConverters.class)
public abstract class CodeFilesDatabase extends RoomDatabase {

    public abstract CodeFilesDao codeFilesDao();

    private static volatile CodeFilesDatabase INSTANCE;

    public  static CodeFilesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CodeFilesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                         CodeFilesDatabase.class,"code_files_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
