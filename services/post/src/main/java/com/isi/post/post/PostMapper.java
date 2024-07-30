package com.isi.post.post;

import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toPost(PostRequest request) {
        if (request == null){
            return null ;
        }return Post.builder()
                .id(request.id())
                .titre(request.titre())
                .description(request.description())
                .build();
    }

    public PostResponse fromPost(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitre(),
                post.getDescription()
        );
    }
}
