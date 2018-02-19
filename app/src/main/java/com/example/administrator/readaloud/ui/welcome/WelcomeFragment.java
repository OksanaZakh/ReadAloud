package com.example.administrator.readaloud.ui.welcome;

import android.content.Intent;
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

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */

public class WelcomeFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {

    public static final String TAG_WELCOME = "TAG_WELCOME";

    private ImageButton avaButton;
    private Button startReadButton;
    private EditText userName;

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
            createNewUser();
            Intent intent = new Intent(getContext(), BaseActivity.class);
            getActivity().finish();
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), R.string.welcome_fragment_toast_put_user_name, Toast.LENGTH_SHORT).show();
        }
    }

    public void createNewUser() {
        String name = userName.getText().toString().trim();
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        if (!handler.getUserListDB().isUserInBase(name)) {
            UUID uuid = UUID.randomUUID();
            String UUIDString = uuid.toString();
            UserModel user = new UserModel();
            user.setName(name);
            user.setToken(UUIDString);
            user.setAvatarId(0);
            handler.getUserListDB().addUser(user);
        }
    }
}
