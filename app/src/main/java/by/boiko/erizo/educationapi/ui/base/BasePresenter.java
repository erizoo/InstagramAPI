package by.boiko.erizo.educationapi.ui.base;

import javax.inject.Inject;

import by.boiko.erizo.educationapi.data.RepositoryManager;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {


    private final RepositoryManager repositoryManager;
    private final CompositeDisposable compositeDisposable;

    private V mvpView;

    @Inject
    public BasePresenter(RepositoryManager repositoryManager, CompositeDisposable compositeDisposable) {
        this.repositoryManager = repositoryManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    @Override
    public V getMvpView() {
        return mvpView;
    }

    @Override
    public CompositeDisposable getCompositeDisposable(){
        return compositeDisposable;
    }

    @Override
    public RepositoryManager getRepositoryManager(){
        return repositoryManager;
    }

}
