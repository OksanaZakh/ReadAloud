package com.example.administrator.readaloud.app.ui.info;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.administrator.readaloud.R;

public class InfoDialogFragment extends DialogFragment {
    public static final String TAG = "InfoDialogFragment";

    public static InfoDialogFragment newInstance() {
        return new InfoDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_info, container, false);
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.TOP | Gravity.RIGHT);
        return v;
    }
}
