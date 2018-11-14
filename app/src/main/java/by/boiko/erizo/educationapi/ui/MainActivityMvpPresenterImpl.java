package by.boiko.erizo.educationapi.ui;

import javax.inject.Inject;

import by.boiko.erizo.educationapi.data.RepositoryManager;
import by.boiko.erizo.educationapi.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityMvpPresenterImpl <V extends MainActivityMvpView> extends BasePresenter<V>
        implements MainActivityMvpPresenter<V>{

    @Inject
    public MainActivityMvpPresenterImpl(RepositoryManager repositoryManager, CompositeDisposable compositeDisposable) {
        super(repositoryManager, compositeDisposable);
    }

    @Override
    public void getImages(String id, String csrfToken) {
        getCompositeDisposable().add(
                getRepositoryManager().getServiceNetwork().getImages(id, csrfToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                getMvpView()::onImagesGeted,
                                Throwable::printStackTrace
                        )
        );
    }
}
