package com.example.administrator.readaloud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {

    public static final String TAG_WELCOME_FRAGMENT = "TAG_WELCOME_FRAGMENT";
    private ImageButton avaButton;
    private Button startReadButton;
    private EditText userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome_main, container, false);

        avaButton = rootView.findViewById(R.id.WelcomeFragment_avaImgButton);
        startReadButton = rootView.findViewById(R.id.WelcomeFragment_goToReadSectionButton);
        userName = rootView.findViewById(R.id.WelcomeFragment_userName);

        avaButton.setOnClickListener(this);
        startReadButton.setOnClickListener(this);
        userName.setOnKeyListener(goToReadSectionListenerEnter);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.WelcomeFragment_avaImgButton:
                FragmentManager fragmentManager = getFragmentManager();
                WelcomeUserAvatarDialogFragment avatarDialogFragment = new WelcomeUserAvatarDialogFragment();
                avatarDialogFragment.show(fragmentManager, TAG_WELCOME_FRAGMENT);
                break;

            case R.id.WelcomeFragment_goToReadSectionButton:
                if (!userName.getText().toString().trim().isEmpty()) {
                    startReadSectionActivity();
                } else {
                    Context context = getContext();
                    Toast.makeText(context, R.string.welcome_fragment_toast_put_user_name, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    View.OnKeyListener goToReadSectionListenerEnter = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                startReadSectionActivity();
            }
            return false;
        }
    };

    public void startReadSectionActivity() {
        Intent intent = new Intent(getActivity(), BaseActivity.class);
        startActivity(intent);


    }
}
