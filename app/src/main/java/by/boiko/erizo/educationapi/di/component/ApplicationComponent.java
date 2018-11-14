package by.boiko.erizo.educationapi.di.component;

import android.content.Context;

import javax.inject.Singleton;

import by.boiko.erizo.educationapi.EducationApi;
import by.boiko.erizo.educationapi.data.RepositoryManager;
import by.boiko.erizo.educationapi.di.ApplicationContext;
import by.boiko.erizo.educationapi.di.module.ApiModule;
import by.boiko.erizo.educationapi.di.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(EducationApi application);

    @ApplicationContext
    Context context();

    RepositoryManager getRepositoryManager();

}

