package com.example.administrator.readaloud.app.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.administrator.readaloud.app.core.BeanContext;
import com.example.administrator.readaloud.app.core.activities.AppActivity;
import com.example.administrator.readaloud.businessservice.BusinessService;
import com.example.administrator.readaloud.businessservice.BusinessServiceBase;

/**
 * Created by p-sha on 24.02.2018.
 */

public abstract class AppFragment extends Fragment implements BaseAppFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public AppActivity getAppActivity() {
        return (AppActivity) getActivity();
    }

    @Override
    public BusinessService getBusinessService() {
        return new BusinessServiceBase(getContext());
    }

    @Override
    public BeanContext getBeanContext() {
        return getAppActivity().getBeanContext();
    }
}
