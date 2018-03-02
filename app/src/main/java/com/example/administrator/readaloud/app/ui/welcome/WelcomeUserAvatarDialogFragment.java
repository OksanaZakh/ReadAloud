package com.example.administrator.readaloud.app.ui.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.fragments.AppDialogFragment;
import com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator.ApiService;
import com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator.Avatar;
import com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator.AvatarAdapter;
import com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator.AvatarsList;
import com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator.InternetConnection;
import com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator.RetroClient;



/**
 * Created by Administrator on 26.01.2018.
 */

public class WelcomeUserAvatarDialogFragment extends AppDialogFragment implements View.OnClickListener {

    public static final String TAG_WELCOME_USER_AVATAR = "TAG_WELCOME_USER_AVATAR";

    private Button cancelButton;
    private List<Avatar> avatarList;
    private AvatarAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_welcome_user_ava_main, container, false);
        final GridView gridview = rootView.findViewById(R.id.WelcomeUserAvatarDialogFragment_avaGridView);
        cancelButton = rootView.findViewById(R.id.WelcomeUserAvatarDialogFragment_cancelButton);

        cancelButton.setOnClickListener(this);

        avatarList = new ArrayList<>();

        if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {

            ApiService api = RetroClient.getApiService();

            Call<AvatarsList> call = api.getJSON();

            call.enqueue(new Callback<AvatarsList>() {
                @Override
                public void onResponse(Call<AvatarsList> call, Response<AvatarsList> response) {

                    if (response.isSuccessful()) {
                        avatarList = response.body().getAvatars();
                        adapter = new AvatarAdapter(getContext(), avatarList);
                        gridview.setAdapter(adapter);

                    } else {
                        Toast.makeText(getAppActivity().getApplicationContext(), R.string.dialog_fragm_welcome_main_toast_bad_internet_connection, Toast.LENGTH_LONG);
                    }
                }

                @Override
                public void onFailure(Call<AvatarsList> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.dialog_fragm_welcome_main_toast_bad_internet_connection, Toast.LENGTH_LONG);
                }
            });

        } else {
            Toast.makeText(getActivity().getApplicationContext(), R.string.dialog_fragm_welcome_main_toast_bad_internet_connection, Toast.LENGTH_LONG);
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
