package com.example.administrator.readaloud.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.database.DBHandler;
import com.example.administrator.readaloud.database.DBHelper;
import com.example.administrator.readaloud.ui.BaseActivity;
import com.example.administrator.readaloud.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */

public class WelcomeFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {

    public static final String TAG_WELCOME = "TAG_WELCOME";

    private ImageButton avaButton;
    private Button startReadButton;
    private EditText userName;
    private String name;
    private int avatarId;
    SharedPreferences preferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome_main, container, false);
        avaButton = rootView.findViewById(R.id.WelcomeFragment_avaImgButton);
        startReadButton = rootView.findViewById(R.id.WelcomeFragment_goToReadSectionButton);
        userName = rootView.findViewById(R.id.WelcomeFragment_userName);
        avaButton.setOnClickListener(this);
        startReadButton.setOnClickListener(this);
        userName.setOnKeyListener(this);

        preferences = getContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.WelcomeFragment_avaImgButton:
                WelcomeUserAvatarDialogFragment avatarDialogFragment = new WelcomeUserAvatarDialogFragment();
                avatarDialogFragment.show(getFragmentManager(), WelcomeUserAvatarDialogFragment.TAG_WELCOME_USER_AVATAR);
                break;
            case R.id.WelcomeFragment_goToReadSectionButton:
                startReadSectionActivity();
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            startReadSectionActivity();
        }
        return false;
    }

    public void startReadSectionActivity() {
        if (!userName.getText().toString().trim().isEmpty()) {
            logInUser();
            Intent intent = new Intent(getContext(), BaseActivity.class);
            getActivity().finish();
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), R.string.welcome_fragment_toast_put_user_name, Toast.LENGTH_SHORT).show();
        }
    }

    public void logInUser() {
        name = userName.getText().toString().trim();
        preferences.edit().putString(Constants.APP_PREFERENCES_USER, name).apply();
        // avatarId identification will be here
        avatarId = 0;
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        handler.getUserListDB().makeLogIn(name, avatarId);
    }

}
