package by.boiko.erizo.educationapi.data.network;

import by.boiko.erizo.educationapi.data.ResponseModel.ResponseModel;
import io.reactivex.Observable;

public interface ServiceNetwork {

    Observable<ResponseModel> getImages(String id, String csrfToken);
}
