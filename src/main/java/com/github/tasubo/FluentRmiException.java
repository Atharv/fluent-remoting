package com.github.tasubo;

public class FluentRmiException extends RuntimeException {
    public FluentRmiException() {
    }

    public FluentRmiException(String message) {
        super(message);
    }

    public FluentRmiException(String message, Throwable cause) {
        super(message, cause);
    }

    public FluentRmiException(Throwable cause) {
        super(cause);
    }
}
