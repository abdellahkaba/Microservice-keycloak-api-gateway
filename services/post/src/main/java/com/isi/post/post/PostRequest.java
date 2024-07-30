package com.isi.post.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRequest(
        Integer id,
        @NotNull(message = "Veuillez donner un titre du post")
        String titre,
        String description
) {
}
