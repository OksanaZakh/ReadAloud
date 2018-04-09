package com.example.administrator.readaloud.app.core;

import android.content.Context;

import com.example.administrator.readaloud.businessservice.BusinessService;
import com.example.administrator.readaloud.businessservice.BusinessServiceBase;

/**
 * Created by p-sha on 24.02.2018.
 */

public class BeanFactory {

    protected BusinessService createBusinessService(Context context) {
        return new BusinessServiceBase(context);
    }

}
