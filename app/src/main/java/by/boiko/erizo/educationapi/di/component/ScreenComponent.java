package by.boiko.erizo.educationapi.di.component;

import by.boiko.erizo.educationapi.ui.MainActivity;
import by.boiko.erizo.educationapi.di.PerScreen;
import by.boiko.erizo.educationapi.di.module.ScreenModule;
import dagger.Component;

@PerScreen
@Component(modules = ScreenModule.class, dependencies = ApplicationComponent.class)
public interface ScreenComponent {

    void inject(MainActivity activity);

}
