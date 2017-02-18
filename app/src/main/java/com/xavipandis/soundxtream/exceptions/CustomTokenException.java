package com.xavipandis.soundxtream.exceptions;

import java.io.IOException;

/**
 * Created by Xavi on 15/02/2017.
 */

public class CustomTokenException extends IOException {
    public CustomTokenException(String message) {
        super(message);
    }
}
