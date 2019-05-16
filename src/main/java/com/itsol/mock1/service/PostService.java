package com.itsol.mock1.service;

import com.itsol.mock1.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    List<Post> findAllAndSort();

    List<Post> findPostsByUserIdAndOrderByIdDesc(int id);

    Post findById(int id);

    void save(Post post);

    void deleteById(int id);
}
