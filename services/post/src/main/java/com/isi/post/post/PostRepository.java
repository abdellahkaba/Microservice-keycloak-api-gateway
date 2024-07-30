package com.isi.post.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {
    Optional<Post> findByTitre(String titre) ;
}
