package com.assignmentba.di;

import android.app.Application;

import com.assignmentba.BAApplication;
import com.assignmentba.di.builder.ActivityBuilderModule;
import com.assignmentba.di.module.AppModule;
import com.assignmentba.di.module.NetworkModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;



@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                NetworkModule.class,
                AppModule.class,
                ActivityBuilderModule.class
        }
)
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(BAApplication sampleMovieApp);

}
