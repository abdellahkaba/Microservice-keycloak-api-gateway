package com.isi.post.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
