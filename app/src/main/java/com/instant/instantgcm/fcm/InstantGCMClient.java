package com.instant.instantgcm.fcm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.instant.instantgcm.fcm.net.GCMDataInterface;
import com.instant.instantgcm.fcm.net.GCMRegListener;
import com.instant.instantgcm.fcm.services.InstantGCMPreferences;
import com.instant.instantgcm.fcm.utils.GCMRegisterUtils;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/20/2016.
 */
public class InstantGCMClient {
    private static boolean isReceiverRegistered;

    private static GCMRegListener mGCMRegListener;

    public static void initialise(Context context , GCMDataInterface dataInterface , final GCMRegListener gcmRegListener){
        mGCMRegListener = gcmRegListener;

        registerReceiver(context);

    }

    private static BroadcastReceiver mGCMTokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(InstantGCMPreferences.FCM_REGISTRATION_COMPLETE)){
                if(!TextUtils.isEmpty(GCMRegisterUtils.getGCMRegistrationId(context))){
                    mGCMRegListener.onGCMRegIdReceived(GCMRegisterUtils.getGCMRegistrationId(context));
                }else
                    mGCMRegListener.onGCMRegIdFailure(context.getResources().getString(com.instant.instantgcm.R.string.reg_receive_error));
            }


        }

    };

    private static void registerReceiver(Context context){
        if(!isReceiverRegistered) {

            IntentFilter filter = new IntentFilter();
            filter.addAction(InstantGCMPreferences.FCM_REGISTRATION_COMPLETE);

            LocalBroadcastManager.getInstance(context).registerReceiver(mGCMTokenReceiver,
                    filter);

            isReceiverRegistered = true;
        }
    }

}
