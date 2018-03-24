package com.example.administrator.readaloud.app.ui.read;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
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
import com.example.administrator.readaloud.app.core.fragments.AppFragment;
import com.example.administrator.readaloud.utils.Constants;
import com.example.administrator.readaloud.utils.MuteAudio;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 23.01.2018.
 */

public class ReadSectionFragment extends AppFragment implements View.OnClickListener, RecognitionListener {
    private static final String TAG = "ReadSectionFragment";
    private TextView readTextView;
    private SeekBar timeSeekBar;
    private ImageButton startReadButton;
    private ImageButton restartReadButton;
    private String speechString;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private boolean speechStarted = false;
    private SeekBarHandler seekBarHandler;
    private Timer timer;
    private ReadingTimerTask readingTimerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_read_section_main, container, false);
        readTextView = viewRoot.findViewById(R.id.ReadSectionFragment_ReadingTextView);

        timeSeekBar = viewRoot.findViewById(R.id.ReadSectionFragment_TimeSeekBar);
        seekBarHandler = new SeekBarHandler(getActivity(), timeSeekBar);

        startReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonStart);
        restartReadButton = viewRoot.findViewById(R.id.ReadSectionFragment_ButtonRestart);
        startReadButton.setOnClickListener(this);
        restartReadButton.setOnClickListener(this);

        speechString = "";
        speech = SpeechRecognizer.createSpeechRecognizer(getContext());
        speech.setRecognitionListener(this);

        initialiseRecognitionIntent();
        checkAudioPermission();
        return viewRoot;
    }


    private void checkAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) ==
                    PackageManager.PERMISSION_GRANTED) {
            } else {
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

    private void initialiseRecognitionIntent() {
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 60000);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getContext().getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ReadSectionFragment_ButtonStart:
                if (!speechStarted) {
                    startReading();
                    break;
                } else {
                    stopReading();
                    break;
                }
            case R.id.ReadSectionFragment_ButtonRestart:
                restartReading();
                break;
        }
    }

    private void startReading() {
        startReadButton.setImageResource(R.drawable.ic_pause_black_24px);
        speech.setRecognitionListener(this);
        speechStarted = true;
        MuteAudio.muteAudio(getContext());
        speech.startListening(recognizerIntent);
        timer = new Timer();
        readingTimerTask = new ReadingTimerTask();
        timer.schedule(readingTimerTask, 60000);
        seekBarHandler.runSeekBar();
    }

    private void stopReading() {
        if (timer != null) {
            timer.cancel();
        }
        startReadButton.setImageResource(R.drawable.ic_play_arrow_black_24px);
        speechStarted = false;
        speech.stopListening();
        speech.destroy();
        MuteAudio.unMuteAudio(getContext());
        readTextView.setText(speechString);
        seekBarHandler.pauseSeekBar();
    }

    private void restartReading() {
        if (timer != null) {
            timer.cancel();
        }
        if (speechStarted) {
            stopReading();
        }
        speechString = "";
        readTextView.setText("");
        seekBarHandler.restartSeekBar();
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(TAG, "onBeginningOfSpeech");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(TAG, "onBufferReceived:");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech");
        if (speechStarted) {
            speech.startListening(recognizerIntent);
        }
    }

    @Override
    public void onError(int errorCode) {
        Log.d(TAG, "onError");
        if (speechStarted)
            speech.startListening(recognizerIntent);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(TAG, "onPartialResults");
        ArrayList<String> matches = arg0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        readTextView.setText(speechString + matches.get(0));
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speechString = speechString + " " + matches.get(0);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(TAG, "onRmsChanged: " + rmsdB);
    }

    private class ReadingTimerTask extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    stopReading();
                }
            });
        }
    }
}