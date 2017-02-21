package com.xavipandis.soundxtream.managers.track;

import com.xavipandis.soundxtream.model.Track;

import java.util.List;

/**
 * Created by xavi on 18/02/2017.
 */

public interface TrackCallback {
    void onSuccess(List<Track> track);
    void onFailure(Throwable t);
}
