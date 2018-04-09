package com.example.administrator.readaloud.app.core;

import android.content.Context;

import com.example.administrator.readaloud.businessservice.BusinessService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by p-sha on 24.02.2018.
 */

public class BeanContext {

    public static BeanContext getInstance(Context context) {
        return ApplicationHandler.getInstance(context).createBeanContext(context, new BeanFactory());
    }

    public static BeanContext getInstance(Context context, BeanFactory factory) {
        return ApplicationHandler.getInstance(context).createBeanContext(context, factory);
    }

    public BeanContext(Context context, BeanFactory factory) {
        initException = new Exception("BeanContext is not properly closed!");
        this.context = context;
        this.factory = factory;
    }

    private final Exception initException;
    private final Context context;
    private final BeanFactory factory;
    private volatile BusinessService businessService;
    private AtomicBoolean isClosed = new AtomicBoolean(false);

    public Context getContext() {
        return context;
    }

    public BusinessService getBusinessService() {
        if (businessService == null) {
            businessService = factory.createBusinessService(context);
        }
        return businessService;
    }

    public void close() {
        isClosed.set(true);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
