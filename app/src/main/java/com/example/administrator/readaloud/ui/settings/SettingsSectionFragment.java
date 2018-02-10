package com.example.administrator.readaloud.ui.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.readaloud.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class SettingsSectionFragment extends Fragment {

    public static final String TAG_SETTINGS_SECTION = "TAG_SETTINGS_SECTION";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

}
