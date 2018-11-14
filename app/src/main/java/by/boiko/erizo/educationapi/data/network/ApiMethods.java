package by.boiko.erizo.educationapi.data.network;


import by.boiko.erizo.educationapi.data.ResponseModel.ResponseModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiMethods {

    @Headers("X-CSRFToken: tQCXJzVNt6lDjjcMdiF8sgpIf27VVyMA")
    @GET("query")
    Observable<ResponseModel> getImages(@Query("query_hash") String hash,
                                        @Query("variables") String variables);
}
