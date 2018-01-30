package com.example.administrator.readaloud;

import android.content.Context;
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
public class WelcomeFragment extends Fragment {

    public static final String TAG_WELCOME_FRAGMENT = "TAG_WELCOME_FRAGMENT";
    ImageButton avaButton;
    Button startReadButton;
    EditText userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome_main, container, false);

        avaButton = rootView.findViewById(R.id.WelcomeFragment_avaImgButton);
        startReadButton = rootView.findViewById(R.id.WelcomeFragment_goToReadSectionButton);
        userName = rootView.findViewById(R.id.WelcomeFragment_userName);

        avaButton.setOnClickListener(avatarPikerListener);
        startReadButton.setOnClickListener(goToReadSectionListener);
        userName.setOnKeyListener(goToReadSectionListenerEnter);

        return rootView;
    }

    View.OnClickListener avatarPikerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            WelcomeUserAvatarDialogFragment avatarDialogFragment = new WelcomeUserAvatarDialogFragment();
            avatarDialogFragment.show(fragmentManager, TAG_WELCOME_FRAGMENT);
        }
    };

    View.OnClickListener goToReadSectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!userName.getText().toString().equals("")) {
                createReadSectionFragment();
            } else {
                Context context = getActivity().getApplicationContext();
                Toast toast = Toast.makeText(context, R.string.welcome_fragment_toast_put_user_name, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };

    View.OnKeyListener goToReadSectionListenerEnter = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                createReadSectionFragment();
            }
            return false;
        }
    };

    public void createReadSectionFragment() {
        ReadSectionFragment readSectionFragment = new ReadSectionFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.main_fragment_container, readSectionFragment, TAG_WELCOME_FRAGMENT)
                .commit();
    }
}
