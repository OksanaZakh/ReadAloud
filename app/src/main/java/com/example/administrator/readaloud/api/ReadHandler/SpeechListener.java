package com.example.administrator.readaloud.api.ReadHandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.example.administrator.readaloud.utils.MuteAudio;

import java.util.ArrayList;

public class SpeechListener implements RecognitionListener {
    private String speechString;
    private String partialSpeechString;
    private SpeechRecognizer speech;
    private Intent recognizerIntent;
    private Context context;
    private boolean speechStarted = false;
    private static final String TAG = "SpeechListener";

    public SpeechListener(Context context) {
        this.context = context;
        speechString = "";
        speech = SpeechRecognizer.createSpeechRecognizer(context);
        speech.setRecognitionListener(this);
        initialiseRecognitionIntent();
    }

    private void initialiseRecognitionIntent() {
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 2000);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
    }

    public void startReading() {
        speech.setRecognitionListener(this);
        speechStarted = true;
        MuteAudio.muteAudio(context);
        speech.startListening(recognizerIntent);
    }

    public void stopReading() {
        speechStarted = false;
        if (speech != null) {
            speech.stopListening();
            speech.destroy();
        }
        MuteAudio.unMuteAudio(context);
    }

    public void restartReading() {
        speechString = "";
        if (speechStarted) {
            stopReading();
        }
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
        ArrayList<String> matches = arg0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        partialSpeechString = " " + matches.get(0);
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speechString += " ";
        for (String string : matches) {
            speechString += string;
        }
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(TAG, "onRmsChanged: " + rmsdB);
    }

    public String getSpeechString() {
        if (!speechString.contains(partialSpeechString)) {
            speechString += " " + partialSpeechString;
        }
        return speechString;
    }

    public void clearSpeechString() {
        speechString = "";
    }
}
