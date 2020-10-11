package com.example.decoder_app.repository;

import com.example.decoder_app.model.CodeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public interface DcoderAPI {

    @GET(ApiUrls.GET_DATA+"/{page_no}")
    Observable<CodeResponse> getData(@Path("page_no") int pageNo);
}
