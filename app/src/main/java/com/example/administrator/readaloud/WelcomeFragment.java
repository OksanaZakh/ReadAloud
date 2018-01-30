package com.example.administrator.readaloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    public static final String TAG_WELCOME_FRAGMENT = "TAG_WELCOME_FRAGMENT";
    ImageButton avaButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome_main, container, false);

        avaButton = rootView.findViewById(R.id.WelcomeFragment_AvaImgButton);
        avaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final WelcomeUserAvatarDialogFragment avatarDialogFragment = new WelcomeUserAvatarDialogFragment();
                avatarDialogFragment.show(fragmentManager, TAG_WELCOME_FRAGMENT);
            }
        });

        return rootView;
    }

}
