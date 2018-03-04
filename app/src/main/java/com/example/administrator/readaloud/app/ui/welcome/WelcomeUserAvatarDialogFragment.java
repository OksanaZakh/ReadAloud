package com.example.administrator.readaloud.app.ui.welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.administrator.readaloud.api.AvatarsCreator.ApiService;
import com.example.administrator.readaloud.api.AvatarsCreator.Avatar;
import com.example.administrator.readaloud.api.AvatarsCreator.AvatarAdapter;
import com.example.administrator.readaloud.api.AvatarsCreator.AvatarsList;
import com.example.administrator.readaloud.utils.Constants;
import com.example.administrator.readaloud.utils.InternetConnection;
import com.example.administrator.readaloud.api.AvatarsCreator.RetroClient;



/**
 * Created by Administrator on 26.01.2018.
 */

public class WelcomeUserAvatarDialogFragment extends AppDialogFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String TAG_WELCOME_USER_AVATAR = "TAG_WELCOME_USER_AVATAR";

    private Button cancelButton;
    private List<Avatar> avatarList;
    private AvatarAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_welcome_user_ava_main, container, false);
        final GridView avatarGridView = rootView.findViewById(R.id.WelcomeUserAvatarDialogFragment_avaGridView);
        cancelButton = rootView.findViewById(R.id.WelcomeUserAvatarDialogFragment_cancelButton);

        cancelButton.setOnClickListener(this);
        avatarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dismiss();
            }
        });

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
                        avatarGridView.setAdapter(adapter);
                        avatarGridView.setOnItemClickListener(WelcomeUserAvatarDialogFragment.this);

                    } else {
                        Toast.makeText(getContext(), R.string.dialog_fragm_welcome_main_toast_bad_internet_connection, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<AvatarsList> call, Throwable t) {
                    Toast.makeText(getContext(), R.string.dialog_fragm_welcome_main_toast_bad_internet_connection, Toast.LENGTH_LONG).show();
                }
            });

        } else {
            Toast.makeText(getContext(), R.string.dialog_fragm_welcome_main_toast_bad_internet_connection, Toast.LENGTH_LONG).show();
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        startWelcomeFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String imageUrl = avatarList.get(position).getMedia().getImageUrl();
        SharedPreferences preferences = getContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
        preferences.edit().putString(Constants.APP_PREFERENCES_AVATAR, imageUrl).apply();
        dismiss();
        startWelcomeFragment();
    }

    private void startWelcomeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_container, new WelcomeFragment())
                .commit();
    }
}
