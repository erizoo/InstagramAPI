package by.boiko.erizo.educationapi;

import android.app.Application;

import by.boiko.erizo.educationapi.di.component.ApplicationComponent;
import by.boiko.erizo.educationapi.di.component.DaggerApplicationComponent;
import by.boiko.erizo.educationapi.di.module.ApplicationModule;

public class EducationApi extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}