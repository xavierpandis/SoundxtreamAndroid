package com.xavipandis.soundxtream.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xavipandis.soundxtream.R;
import com.xavipandis.soundxtream.managers.TokenStoreManager;

/**
 * Created by Xavi on 18/02/2017.
 */

public class MainFragment extends Fragment {

    private static final String ARGUMENT_TOKEN = "TOKEN";
    private static final String ARGUMENT_USERNAME = "USERNAME";
    private static final String ARGUMENT_REFRESH_TOKEN = "REFRESH";

    TextView accessTokenView;
    TextView userConnectedView;

    public static MainFragment newInstance(String token, String refreshToken, String username) {
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_TOKEN, token);
        args.putString(ARGUMENT_REFRESH_TOKEN, refreshToken);
        args.putString(ARGUMENT_USERNAME, username);
        final MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        accessTokenView = (TextView) view.findViewById(R.id.tokenUser);
        userConnectedView = (TextView) view.findViewById(R.id.userConnected);

        Bundle args = getArguments();

        Log.d("nxw => TOKEN", args.getString(ARGUMENT_TOKEN));
        Log.d("nxw => REFRESH", args.getString(ARGUMENT_REFRESH_TOKEN));
        Log.d("nxw => USERNAME", args.getString(ARGUMENT_USERNAME));

        accessTokenView.setText("Token:\n" + args.getString(ARGUMENT_TOKEN) + " \nRefresh_token:" + args.getString(ARGUMENT_REFRESH_TOKEN));
        userConnectedView.setText("WELCOME: " + args.getString(ARGUMENT_USERNAME));
        return view;
    }

}
