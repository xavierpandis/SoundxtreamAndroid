package com.xavipandis.soundxtream.activities.track;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xavipandis.soundxtream.R;
import com.xavipandis.soundxtream.managers.track.TrackManager;
import com.xavipandis.soundxtream.model.Track;
import com.xavipandis.soundxtream.util.BlurBuilder;
import com.xavipandis.soundxtream.util.CustomProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Xavi on 17/02/2017.
 */

public class TrackDetailFragment extends Fragment {

    public static TrackDetailFragment newInstance() {
        return new TrackDetailFragment();
    }

    Toolbar appBarLayout;
    Activity activity;

    public static final String ARG_ITEM_ID = "item_id";

    private Track track;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this.getActivity();
        appBarLayout = (Toolbar) activity.findViewById(R.id.toolbar);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String id = getArguments().getString(ARG_ITEM_ID);
            track = TrackManager.getInstance().getTrack(id);

            if (appBarLayout != null) {
                appBarLayout.setTitle(track.getName());
            }
        }
    }

    public static int getDominantColor(Bitmap bitmap) {
        List<Palette.Swatch> swatchesTemp = Palette.from(bitmap).generate().getSwatches();
        List<Palette.Swatch> swatches = new ArrayList<Palette.Swatch>(swatchesTemp);
        Collections.sort(swatches, new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch swatch1, Palette.Swatch swatch2) {
                return swatch2.getPopulation() - swatch1.getPopulation();
            }
        });
        return swatches.size() > 0 ? swatches.get(0).getRgb() : Color.rgb(0,0,0);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Activity activity = this.getActivity();
        Toolbar appBarLayout = (Toolbar) activity.findViewById(R.id.toolbar);
        if (appBarLayout != null) {
            appBarLayout.setTitle(getString(R.string.app_name));
            appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_detail, container, false);
        final ImageView app_bar_image = (ImageView) view.findViewById(R.id.app_bar_image);

        TextView trackName = (TextView) view.findViewById(R.id.trackDetailName);
        TextView trackArtist = (TextView) view.findViewById(R.id.trackDetailArtistName);
        final ImageView imageView = (ImageView) view.findViewById(R.id.track_detail_image);

        trackName.setText(track.getName());
        trackArtist.setText(track.getUser().getLogin());
        int alpha = (int)(0.2 * 255.0f);
        trackName.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        trackArtist.setBackgroundColor(Color.argb(alpha, 0, 0, 0));

        Picasso.with(activity)
                .load(CustomProperties.baseUrl + "/" +track.getArtwork())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmapArtwork = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                        final int defaultColor = 0x000000;

                        int colorBar = getDominantColor(bitmapArtwork);

                        appBarLayout.setBackgroundColor(colorBar);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = activity.getWindow();

                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                            // finally change the color
                            window.setStatusBarColor(colorBar);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });



        Picasso.with(activity)
                .load(CustomProperties.baseUrl + "/" +track.getArtwork())
                .into(app_bar_image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) app_bar_image.getDrawable()).getBitmap();

                        Bitmap blurredBitmap = BlurBuilder.blur( getActivity(), bitmap );

                        app_bar_image.setImageDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );

                    }

                    @Override
                    public void onError() {

                    }
                });

        return view;
    }
}

