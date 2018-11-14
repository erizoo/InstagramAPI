package by.boiko.erizo.educationapi.ui;

import by.boiko.erizo.educationapi.ui.base.MvpPresenter;

public interface MainActivityMvpPresenter <V extends MainActivityMvpView> extends MvpPresenter<V> {

    void getImages(String id, String csrfToken);
}
