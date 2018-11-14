package by.boiko.erizo.educationapi.ui;

import by.boiko.erizo.educationapi.data.ResponseModel.ResponseModel;
import by.boiko.erizo.educationapi.ui.base.MvpView;

public interface MainActivityMvpView extends MvpView {

    void onImagesGeted(ResponseModel responseModel);
}
