package com.example.administrator.readaloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Administrator on 23.01.2018.
 */

public class ReadSectionFragment extends Fragment {

    TextView readTextView;
    SeekBar timeSeekBar;
    ImageButton startReadButton;
    ImageButton restartReadButton;
    ImageButton pauseReadButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_read_section_main, container, false);

        readTextView = viewRoot.findViewById(R.id.ReadSectionFagment_ReadingTextView);
        timeSeekBar = viewRoot.findViewById(R.id.ReadSectionFragment_TimeSeekBar);
        startReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonStart);
        restartReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonRestart);
        pauseReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonPause);

        //make menu button visible
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return viewRoot;
    }

}