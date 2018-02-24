package com.example.administrator.readaloud.app.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.BeanContext;
import com.example.administrator.readaloud.app.core.activities.AppActivity;
import com.example.administrator.readaloud.businessservice.BusinessService;

/**
 * Created by p-sha on 24.02.2018.
 */

public abstract class AppDialogFragment extends DialogFragment implements BaseAppFragment {

    private BeanContext beanContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (beanContext != null) {
            beanContext.close();
            beanContext = null;
        }
        beanContext = BeanContext.getInstance(getContext());
        super.onCreate(savedInstanceState);
    }

    @Override
    public AppActivity getAppActivity() {
        return (AppActivity) getActivity();
    }

    @Override
    public BusinessService getBusinessService() {
        return beanContext.getBusinessService();
    }

    @Override
    public BeanContext getBeanContext() {
        return beanContext;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (beanContext != null) {
            beanContext.close();
            beanContext = null;
        }
    }

    @Override
    public int getTheme() {
        return R.style.AppTheme;
    }
}
