package com.isi.post.post;

public record UpdatePostRequest(
        Integer id,
        String titre,
        String description
) {
}
