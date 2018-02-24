package com.example.administrator.readaloud.app.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.fragments.AppFragment;

public class SettingsSectionFragment extends AppFragment {

    public static final String TAG_SETTINGS_SECTION = "TAG_SETTINGS_SECTION";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

}
