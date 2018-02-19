package com.example.administrator.readaloud.ui.read;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.readaloud.R;


/**
 * Created by Administrator on 23.01.2018.
 */

public class ReadSectionFragment extends Fragment {

    public static final String TAG_READ_SECTION = "TAG_READ_SECTION";

    private TextView readTextView;
    private SeekBar timeSeekBar;
    private ImageButton startReadButton;
    private ImageButton restartReadButton;
    private ImageButton pauseReadButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_read_section_main, container, false);
        readTextView = viewRoot.findViewById(R.id.ReadSectionFragment_ReadingTextView);
        timeSeekBar = viewRoot.findViewById(R.id.ReadSectionFragment_TimeSeekBar);
        startReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonStart);
        restartReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonRestart);
        pauseReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonPause);

        return viewRoot;
    }

}