package com.isi.employe.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
