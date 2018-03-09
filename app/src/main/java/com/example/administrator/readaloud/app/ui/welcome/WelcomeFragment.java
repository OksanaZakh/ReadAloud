package com.example.administrator.readaloud.app.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.fragments.AppFragment;
import com.example.administrator.readaloud.app.ui.BaseActivity;
import com.example.administrator.readaloud.app.core.ApplicationHandler;
import com.example.administrator.readaloud.utils.Constants;
import com.squareup.picasso.Picasso;


public class WelcomeFragment extends AppFragment implements View.OnClickListener, View.OnKeyListener {

    public static final String TAG_WELCOME = "TAG_WELCOME";

    private ImageButton avaButton;
    private Button startReadButton;
    private EditText userName;
    private String name;
    private String avatarUrl;
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

        String imageUrl = preferences.getString(Constants.APP_PREFERENCES_AVATAR, "");
        if (!imageUrl.equals("")) {
            Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.ic_account_circle_black_24px)
                    .error(R.drawable.ic_account_circle_black_24px).into(avaButton);
        }

        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.WelcomeFragment_avaImgButton:
                WelcomeUserAvatarDialogFragment avatarDialogFragment = new WelcomeUserAvatarDialogFragment();
                avatarDialogFragment.show(getFragmentManager(), WelcomeUserAvatarDialogFragment.TAG_WELCOME_USER_AVATAR);
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
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
        avatarUrl = "" + preferences.getString(Constants.APP_PREFERENCES_AVATAR, "");
        ((ApplicationHandler) getActivity().getApplication()).getHandler().getUserListDB().makeLogIn(name, avatarUrl);
    }

}
