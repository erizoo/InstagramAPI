package by.boiko.erizo.educationapi.ui.base;


import by.boiko.erizo.educationapi.data.RepositoryManager;
import io.reactivex.disposables.CompositeDisposable;

public interface MvpPresenter<V> {

    void onAttach(V mvpView);

    void onDetach();

    void onDestroy();

    CompositeDisposable getCompositeDisposable();

    V getMvpView();

    RepositoryManager getRepositoryManager();

}
