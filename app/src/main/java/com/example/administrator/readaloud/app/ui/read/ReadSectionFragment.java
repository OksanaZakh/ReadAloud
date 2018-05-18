package com.example.administrator.readaloud.app.ui.read;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.api.ReadHandler.SeekBarHandler;
import com.example.administrator.readaloud.api.ReadHandler.SpeechListener;
import com.example.administrator.readaloud.app.core.fragments.AppFragment;
import com.example.administrator.readaloud.utils.Constants;

/**
 * Created by Administrator on 23.01.2018.
 */

public class ReadSectionFragment extends AppFragment implements View.OnClickListener, SeekBarHandler.OnSpeechListener {
    private static final String TAG = "ReadSectionFragment";
    private TextView readTextView;
    private SeekBar timeSeekBar;
    private ImageButton startReadButton;
    private ImageButton restartReadButton;
    private SeekBarHandler seekBarHandler;
    private boolean speechStarted = false;
    private SpeechListener listener;
    private String speechString;

    public enum ReadingStatus {OFF, ON, PAUSE, RESTART}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_read_section_main, container, false);
        readTextView = viewRoot.findViewById(R.id.ReadSectionFragment_ReadingTextView);
        timeSeekBar = viewRoot.findViewById(R.id.ReadSectionFragment_TimeSeekBar);
        startReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonStart);
        restartReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonRestart);
        seekBarHandler = new SeekBarHandler(getActivity(), timeSeekBar, this);
        startReadButton.setOnClickListener(this);
        restartReadButton.setOnClickListener(this);
        checkAudioPermission();
        listener = new SpeechListener(getContext());
        speechString = "";
        return viewRoot;
    }

    private void checkAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(getContext(), R.string.read_section_fragment_audio_permission_request, Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO
                }, Constants.REQUEST_RECORD_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_AUDIO_PERMISSION_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(),
                        R.string.read_section_fragment_audio_permission_not_provided, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ReadSectionFragment_ButtonStart:
                if (!speechStarted) {
                    seekBarHandler.runSeekBar();
                    break;
                } else {
                    seekBarHandler.pauseSeekBar();
                    break;
                }
            case R.id.ReadSectionFragment_ButtonRestart:
                seekBarHandler.restartSeekBar();
                speechString = "";
                readTextView.setText(speechString);
                break;
        }
    }

    private void startReading() {
        listener.startReading();
        startReadButton.setImageResource(R.drawable.ic_pause_black_24px);
        speechStarted = true;
    }

    private void stopReading() {
        startReadButton.setImageResource(R.drawable.ic_play_arrow_black_24px);
        speechStarted = false;
        listener.stopReading();
        speechString = listener.getSpeechString();
        readTextView.setText(speechString);
    }

    private void restartReading() {
        startReadButton.setImageResource(R.drawable.ic_play_arrow_black_24px);
        listener.restartReading();
        speechStarted = false;
    }

    @Override
    public void onSpeechListen(ReadingStatus status) {
        switch (status) {
            case OFF:
                stopReading();
                listener.clearSpeechString();
                speechString = "";
                break;
            case PAUSE:
                stopReading();
                break;
            case ON:
                startReading();
                break;
            case RESTART:
                restartReading();
                break;
        }
    }
}