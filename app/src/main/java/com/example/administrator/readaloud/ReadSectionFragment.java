package com.example.administrator.readaloud;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 23.01.2018.
 */

public class ReadSectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_read_section_main, container, false);

        return viewRoot;
    }
}