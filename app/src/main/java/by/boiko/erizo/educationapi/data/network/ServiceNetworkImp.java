package by.boiko.erizo.educationapi.data.network;

import javax.inject.Inject;

import by.boiko.erizo.educationapi.data.ResponseModel.ResponseModel;
import io.reactivex.Observable;

public class ServiceNetworkImp implements ServiceNetwork {

    private static final String TAG = ServiceNetworkImp.class.getSimpleName();

    private ApiMethods apiMethods;

    @Inject
    public ServiceNetworkImp(ApiMethods apiMethods) {
        this.apiMethods = apiMethods;
    }

    @Override
    public Observable<ResponseModel> getImages(String id, String csrfToken) {
        String hash = "42323d64886122307be10013ad2dcc44";
        String json = "{\"id\":\"1397677700\",\"first\":15}";
        return apiMethods.getImages(hash, json);
    }
}
