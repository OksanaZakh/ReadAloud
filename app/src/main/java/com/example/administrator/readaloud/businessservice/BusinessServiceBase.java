package com.example.administrator.readaloud.businessservice;

import android.content.Context;

import com.example.administrator.readaloud.app.core.BeanContext;

/**
 * Created by p-sha on 24.02.2018.
 */

public class BusinessServiceBase implements BusinessService {

    public BusinessServiceBase(BeanContext beanContext) {
        this.beanContext = beanContext;
        this.context = beanContext.getContext();
    }

    private final BeanContext beanContext;
    private final Context context;

}
