package com.example.decoder_app.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

import com.example.decoder_app.filter.FileLanguageFilter;
import com.example.decoder_app.filter.Languages;
import com.example.decoder_app.filter.ProjectLanguageFilter;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */
public class BasicUtils {

    public static void hideKeyboard(@NonNull Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String toCamelCase(String value) {
        if (value == null) {
            return null;
        } else if (value.length() == 0) {
            return value;
        } else if (value.length() > 1) {
            return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        } else {
            return value.toUpperCase();
        }
    }

    public static int getFolderLanguageSelected(String value) {
        switch (value) {
            case Languages.HTML : return ProjectLanguageFilter.HTML;
            case Languages.JAVA :  return ProjectLanguageFilter.JAVA;
            case Languages.PYTHON_3 : return ProjectLanguageFilter.PYTHON_3;
            case Languages.CPP : return ProjectLanguageFilter.CPP;
            case Languages.VB_NET : return ProjectLanguageFilter.VB_NET;
            case Languages.C_SHARP : return ProjectLanguageFilter.C_SHARP;
            case Languages.PYTHON_2 : return ProjectLanguageFilter.PYTHON_2;
            case Languages.NODE_JS : return ProjectLanguageFilter.NODE_JS;
            case Languages.C_KEY : return ProjectLanguageFilter.C_KEY;
            case Languages.SCHEME : return ProjectLanguageFilter.SCHEME;
            default: return ProjectLanguageFilter.ALL;
        }
    }

    public static int getFileLanguageSelected(String value) {
        switch (value) {
            case Languages.HTML : return FileLanguageFilter.HTML;
            case Languages.JAVA :  return FileLanguageFilter.JAVA;
            case Languages.PYTHON_3 : return FileLanguageFilter.PYTHON_3;
            case Languages.CPP : return FileLanguageFilter.CPP;
            case Languages.PYTHON_2 : return FileLanguageFilter.PYTHON_2;
            case Languages.SCALA : return FileLanguageFilter.SCALA;
            case Languages.C : return FileLanguageFilter.C;
            default: return FileLanguageFilter.ALL;
        }
    }
}
