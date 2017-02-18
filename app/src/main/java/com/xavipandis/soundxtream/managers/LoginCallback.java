package com.xavipandis.soundxtream.managers;

import com.xavipandis.soundxtream.model.UserToken;

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}
