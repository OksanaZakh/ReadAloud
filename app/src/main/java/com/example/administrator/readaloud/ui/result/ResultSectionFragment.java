package com.example.administrator.readaloud.ui.result;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.readaloud.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultSectionFragment extends Fragment {

    public static final String TAG_RESULT_SECTION = "TAG_RESULT_SECTION";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }


}
