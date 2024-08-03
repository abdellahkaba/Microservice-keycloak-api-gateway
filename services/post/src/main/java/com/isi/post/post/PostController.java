package com.isi.post.post;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService service ;
    @PostMapping
    public ResponseEntity<Integer> newPost(
            @RequestBody @Valid PostRequest request
    ){
        return ResponseEntity.ok(service.newPost(request));
    }
    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{post-id}")
    public ResponseEntity<PostResponse> findById(
            @PathVariable ("post-id") Integer postId
    ){
        return ResponseEntity.ok(service.findById(postId));
    }
    @GetMapping("/exists/{post-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("post-id") Integer postId
    ){
        return ResponseEntity.ok(service.existById(postId));
    }
    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updatePost(@PathVariable("post-id") Integer id, @RequestBody @Valid UpdatePostRequest request) {
        request = new UpdatePostRequest(id,
                request.titre(),
                request.description());
        service.updatePost(request);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable("post-id") Integer postId)
    {
       service.deletePost(postId);
       return ResponseEntity.accepted().build() ;
    }
}
