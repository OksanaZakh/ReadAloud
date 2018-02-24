package com.example.administrator.readaloud.app.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.fragments.AppFragment;

public class ResultSectionFragment extends AppFragment {

    public static final String TAG_RESULT_SECTION = "TAG_RESULT_SECTION";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

}
