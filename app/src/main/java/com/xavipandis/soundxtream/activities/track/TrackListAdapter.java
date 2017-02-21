package com.xavipandis.soundxtream.activities.track;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xavipandis.soundxtream.R;
import com.xavipandis.soundxtream.activities.MainActivity;
import com.xavipandis.soundxtream.model.Track;
import com.xavipandis.soundxtream.util.CustomProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavi on 18/02/2017.
 */

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHolder> {
    private List<Track> trackList;
    Fragment fragmentOne;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public final View mView;
        public TextView nameTrackView;
        public TextView artistTrackView;
        public ImageView artworkView;
        public Track track;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            nameTrackView = (TextView) v.findViewById(R.id.nameTrack);
            artistTrackView = (TextView) v.findViewById(R.id.artistTrack);
            artworkView = (ImageView) v.findViewById(R.id.artworkTrack);
        }
    }

    Context context;

    public void remove(String item) {
        int position = trackList.indexOf(item);
        trackList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrackListAdapter(List<Track> tracks, Fragment fragmentOne, Context cntxt) {
        trackList = tracks;
        this.fragmentOne = fragmentOne;
        context = cntxt;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_list_content, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.track = trackList.get(position);
        holder.nameTrackView.setText(trackList.get(position).getName());
        holder.artistTrackView.setText(trackList.get(position).getUser().getLogin());

        Picasso.with(context)
                .load(CustomProperties.baseUrl + "/" +trackList.get(position).getArtwork())
                .into(holder.artworkView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TrackDetailFragment();
                Bundle args = new Bundle();
                args.putString(TrackDetailFragment.ARG_ITEM_ID, holder.track.getId().toString());
                fragment.setArguments(args);
                FragmentTransaction transaction = fragmentOne.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trackList.size();
    }
}
