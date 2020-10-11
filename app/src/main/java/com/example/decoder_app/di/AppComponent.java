package com.example.decoder_app.di;

import android.app.Application;

import com.example.decoder_app.adapter.CodeFilesBoundaryCallback;
import com.example.decoder_app.repository.DcoderRepository;
import com.example.decoder_app.view_model.DcoderViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */

@Singleton
@Component (modules = MainModule.class)
public interface AppComponent {

    DcoderRepository getRepository();

    void inject(DcoderViewModel model);

    CodeFilesBoundaryCallback getCodeFilesBoundaryCallback();


    final class Initializer {
        public static AppComponent initialize(Application application) {
            return DaggerAppComponent.builder()
                    .mainModule(new MainModule(application))
                    .build();
        }
    }
}

