package com.instant.instantgcm.middleware.net;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/21/2016.
 */
public interface InstantGCMDeregistrationListener {

    void onDeregisterSuccess();

    void onDeregisterFailure(String failureMsg);
}
