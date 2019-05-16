package com.itsol.mock1.service.impl;

import com.itsol.mock1.model.Post;
import com.itsol.mock1.repository.PostRepository;
import com.itsol.mock1.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;


    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllAndSort(){
        return postRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Post> findPostsByUserIdAndOrderByIdDesc(int id){
        return postRepository.findPostsByUserIDAndSortDESC(id);
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }
}
