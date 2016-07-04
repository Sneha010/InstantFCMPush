package com.instant.instantgcm.middleware.net;



import com.instant.instantgcm.middleware.model.PushPayload;
import com.instant.instantgcm.middleware.model.PushResponse;
import com.instant.instantgcm.middleware.model.UpdateFavoriteRequest;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/14/2016.
 */

public interface MiddlewareAPI {

    // POST with a JSON body

    //Constructor of PushPayloadBean with 3 parameters will be used
    @POST("Registration")
    Call<PushResponse> registerCall(
            @Body PushPayload payloadData
    );

    //Constructor of PushPayloadBean with 2 parameters will be used
    @POST("Deregistration")
    Call<PushResponse> deRegisterCall(
            @Body PushPayload payloadData
    );


    @POST("UpdateFavorites")
    Call<PushResponse> postFavoritesCall(
            @Body UpdateFavoriteRequest payloadData
    );


}
