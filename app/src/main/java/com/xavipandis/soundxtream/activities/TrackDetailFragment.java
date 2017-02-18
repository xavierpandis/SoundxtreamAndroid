package com.xavipandis.soundxtream.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xavipandis.soundxtream.R;
import com.xavipandis.soundxtream.util.BlurBuilder;

/**
 * Created by Xavi on 17/02/2017.
 */

public class TrackDetailFragment extends Fragment {

    public static TrackDetailFragment newInstance() {
        return new TrackDetailFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Activity activity = this.getActivity();
            Toolbar appBarLayout = (Toolbar) activity.findViewById(R.id.toolbar);
            if (appBarLayout != null) {
                appBarLayout.setTitle("EVOLVE");
            }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Activity activity = this.getActivity();
        Toolbar appBarLayout = (Toolbar) activity.findViewById(R.id.toolbar);
        if (appBarLayout != null) {
            appBarLayout.setTitle(getString(R.string.app_name));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_track_detail, container, false);
        ImageView app_bar_image = (ImageView) view.findViewById(R.id.app_bar_image);

        Bitmap bitmap = ((BitmapDrawable)app_bar_image.getDrawable()).getBitmap();

        Bitmap blurredBitmap = BlurBuilder.blur( getActivity(), bitmap );

        app_bar_image.setImageDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );

        return view;
    }
}

