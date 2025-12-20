package com.ceniuch.bluediamondbackend.errors;

public record ErrorMessageModel(
        int status,
        String message
) {
}
