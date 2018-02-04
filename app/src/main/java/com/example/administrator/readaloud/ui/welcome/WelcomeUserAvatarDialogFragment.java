package com.example.administrator.readaloud.ui.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.administrator.readaloud.R;

/**
 * Created by Administrator on 26.01.2018.
 */

public class WelcomeUserAvatarDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG_WELCOME_USER_AVATAR = "TAG_WELCOME_USER_AVATAR";

    private Button cancelButton;
    private GridView avatarsGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_welcome_user_ava_main, container, false);
        avatarsGridView = rootView.findViewById(R.id.WelcomeUserAvatarDialogFragment_avaGridView);
        cancelButton = rootView.findViewById(R.id.WelcomeUserAvatarDialogFragment_cancelButton);
        cancelButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
