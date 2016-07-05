package com.instant.instantgcm;

import android.os.Bundle;
import android.widget.Toast;

import com.instant.instantgcm.middleware.GCMMiddlewareClient;
import com.instant.instantgcm.middleware.model.Chipcode;
import com.instant.instantgcm.middleware.model.Favorites;
import com.instant.instantgcm.middleware.model.PushResponse;
import com.instant.instantgcm.middleware.model.UpdateFavoriteRequest;
import com.instant.instantgcm.middleware.net.InstantGCMDeregistrationListener;
import com.instant.instantgcm.middleware.net.InstantGCMPostFavListener;
import com.instant.instantgcm.middleware.net.InstantGCMRegistrationListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*Main activity class for rasing HTTP Post request*/
public class RequestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.regButton)
    public void registerButtonClicked(){

        GCMMiddlewareClient.register(RequestActivity.this , new MiddlewareDataInterfaceImpl(),
                new InstantGCMRegistrationListener() {
                    @Override
                    public void onRegisterSuccess() {
                        Toast.makeText(RequestActivity.this, "Register Successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRegisterFailure(String failureMsg) {
                        Toast.makeText(RequestActivity.this, failureMsg, Toast.LENGTH_SHORT).show();
                    }


                });

    }


    @OnClick(R.id.deregButton)
    public void deregisterButtonClicked(){

        GCMMiddlewareClient.deRegister(RequestActivity.this, new MiddlewareDataInterfaceImpl(),
                new InstantGCMDeregistrationListener() {
                    @Override
                    public void onDeregisterSuccess() {
                        Toast.makeText(RequestActivity.this, "Deregister Successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDeregisterFailure(String failureMsg) {
                        Toast.makeText(RequestActivity.this, failureMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @OnClick(R.id.postFav)
    public void postFavButtonClicked(){

        UpdateFavoriteRequest requestBean = new UpdateFavoriteRequest();

        Favorites bean = new Favorites();

        Chipcode bib1 = new Chipcode();
        bib1.setFav("24586");
        Chipcode bib2 = new Chipcode();
        bib2.setFav("51629");

        ArrayList<Chipcode> bibList = new ArrayList<>();
        bibList.add(bib1);
        bibList.add(bib2);

        bean.setChipcode(bibList);

        requestBean.setFavorites(bean);

       GCMMiddlewareClient.postFavorites(RequestActivity.this, requestBean ,
               new MiddlewareDataInterfaceImpl(),
               new InstantGCMPostFavListener() {
                   @Override
                   public void onPostFavUpdatedSuccess(PushResponse pushResponse) {
                       Toast.makeText(RequestActivity.this, pushResponse.getMessage(), Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onPostFavUpdatedFailure(String failureMsg) {
                       Toast.makeText(RequestActivity.this, failureMsg, Toast.LENGTH_SHORT).show();
                   }
               });

    }

}
