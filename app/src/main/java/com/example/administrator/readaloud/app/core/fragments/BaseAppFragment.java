package com.example.administrator.readaloud.app.core.fragments;

import com.example.administrator.readaloud.app.core.BeanContext;
import com.example.administrator.readaloud.app.core.activities.AppActivity;
import com.example.administrator.readaloud.businessservice.BusinessService;

/**
 * Created by p-sha on 24.02.2018.
 */

public interface BaseAppFragment {

    AppActivity getAppActivity();

    BusinessService getBusinessService();

    BeanContext getBeanContext();

}
