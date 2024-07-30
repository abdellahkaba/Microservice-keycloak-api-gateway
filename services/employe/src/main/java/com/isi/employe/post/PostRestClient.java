package com.isi.employe.post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "post-service",
        url = "${application.config.post-url}"
)
public interface PostRestClient {

    @GetMapping("/{post-id}")
    Optional<PostResponse> findPostById(
            @PathVariable("post-id") Integer postId
    );
}
