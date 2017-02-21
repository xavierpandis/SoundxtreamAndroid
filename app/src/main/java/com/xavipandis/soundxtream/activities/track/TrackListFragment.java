package com.xavipandis.soundxtream.activities.track;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavipandis.soundxtream.R;
import com.xavipandis.soundxtream.managers.TokenStoreManager;
import com.xavipandis.soundxtream.managers.track.TrackCallback;
import com.xavipandis.soundxtream.managers.track.TrackManager;
import com.xavipandis.soundxtream.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavi on 18/02/2017.
 */

public class TrackListFragment extends Fragment implements TrackCallback, SwipeRefreshLayout.OnRefreshListener {

    List<Track> trackList;
    View view;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public static TrackListFragment newInstance() {
        return new TrackListFragment();
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
            appBarLayout.setTitle("TRACKS");
        }

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_list, container, false);

        List<Track> trackList = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list_tracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new TrackListAdapter(trackList, TrackListFragment.this, view.getContext()));

        TrackManager.getInstance().getTracksUser(TokenStoreManager.getInstance().getUsername(), this);

        return view;
    }

    @Override
    public void onSuccess(List<Track> tracks) {
        trackList = tracks;
        recyclerView.setAdapter(new TrackListAdapter(trackList, TrackListFragment.this, view.getContext()));
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onRefresh() {
        TrackManager.getInstance().getTracksUser(TokenStoreManager.getInstance().getUsername(), this);
    }
}
