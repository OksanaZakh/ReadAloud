package com.example.administrator.readaloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;


/**
 * Created by Administrator on 26.01.2018.
 */

public class WelcomeUserAvatarDialogFragment extends DialogFragment {

    Button cancelButton;
    GridView avatarsGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.dialog_fragment_welcome_user_ava_main, container, false);

        avatarsGridView = viewRoot.findViewById(R.id.WelcomeUserAvatarDialogFragment_avaGridView);
        cancelButton = viewRoot.findViewById(R.id.WelcomeUserAvatarDialogFragment_cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return viewRoot;
    }
}
