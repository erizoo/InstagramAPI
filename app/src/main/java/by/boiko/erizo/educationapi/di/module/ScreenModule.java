package by.boiko.erizo.educationapi.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


import by.boiko.erizo.educationapi.di.ActivityContext;
import by.boiko.erizo.educationapi.di.PerScreen;
import by.boiko.erizo.educationapi.ui.MainActivityMvpPresenter;
import by.boiko.erizo.educationapi.ui.MainActivityMvpPresenterImpl;
import by.boiko.erizo.educationapi.ui.MainActivityMvpView;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ScreenModule {

    private final AppCompatActivity activity;

    public ScreenModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerScreen
    MainActivityMvpPresenter<MainActivityMvpView> provideMainActivityMvpPresenter(MainActivityMvpPresenterImpl<MainActivityMvpView> presenter) {
        return presenter;
    }
}
