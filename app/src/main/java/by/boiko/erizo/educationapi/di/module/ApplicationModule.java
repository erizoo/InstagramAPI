package by.boiko.erizo.educationapi.di.module;

import android.content.Context;

import javax.inject.Singleton;

import by.boiko.erizo.educationapi.EducationApi;
import by.boiko.erizo.educationapi.data.RepositoryManager;
import by.boiko.erizo.educationapi.data.RepositoryManagerImpl;
import by.boiko.erizo.educationapi.di.ApplicationContext;
import by.boiko.erizo.educationapi.di.PerScreen;
import by.boiko.erizo.educationapi.ui.MainActivity;
import by.boiko.erizo.educationapi.ui.MainActivityMvpPresenter;
import by.boiko.erizo.educationapi.ui.MainActivityMvpPresenterImpl;
import by.boiko.erizo.educationapi.ui.MainActivityMvpView;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final EducationApi application;

    public ApplicationModule(EducationApi application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    EducationApi provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    RepositoryManager provideRepositoryManager(RepositoryManagerImpl repositoryManager) {
        return repositoryManager;
    }

}

