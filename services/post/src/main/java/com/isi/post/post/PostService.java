package com.isi.post.post;


import com.isi.post.exception.PostNotFoundException;
import com.isi.post.exception.TitreConflictException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper ;
    public Integer newPost(PostRequest request) {
        if (repository.findByTitre(request.titre()).isPresent()){
            throw new TitreConflictException("Le nom de post existe deja !");
        }
        var post = repository.save(mapper.toPost(request));
        return post.getId();
    }
    public List<PostResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromPost)
                .collect(Collectors.toList());
    }
    public PostResponse findById(Integer postId) {
        return repository.findById(postId)
                .map(mapper::fromPost)
                .orElseThrow(() -> new PostNotFoundException(
                        String.format("Le post non trouvé")
                ));
    }

    public void updatePost(UpdatePostRequest request) {
        var post = repository.findById(request.id())
                .orElseThrow(() -> new PostNotFoundException(
                        String.format("Impossible de modifier non trouvé ID:: %s", request.id())
                ));
        mergePost(post,request) ;
        repository.save(post);
    }
    private void mergePost(Post post, UpdatePostRequest request) {
        if (StringUtils.isNotBlank(request.titre()) &&
                !request.titre().equals(post.getTitre()) &&
                repository.findByTitre(request.titre()).isPresent()) {
            throw new TitreConflictException("Ce Poste existe déjà");
        }
        if (StringUtils.isNotBlank(request.description())) {
            post.setDescription(request.description());
        }
    }
    public void deletePost(Integer postId) {
        repository.deleteById(postId);
    }
}
