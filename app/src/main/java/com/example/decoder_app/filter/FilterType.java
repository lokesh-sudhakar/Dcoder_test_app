package com.example.decoder_app.filter;

import androidx.annotation.StringDef;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */

@StringDef({
        FilterType.File,
        FilterType.Folder
})
public @interface FilterType {
    String File = "file";
    String Folder = "folder";
}
