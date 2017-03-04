package org.gdgsantacruz.readqr.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by svjchrysler on 3/4/17.
 */

public interface IReadQR {

    @GET("Revision/{data}")
    Call<List<Object>> checkQR(@Path("data") String data);

}
