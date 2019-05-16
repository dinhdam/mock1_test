package com.itsol.mock1.repository;

import com.itsol.mock1.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    //sap xep post moi nhat len tren dau
    @Query("SELECT p FROM Post p WHERE p.user.id = :id ORDER BY p.id DESC")
    List<Post> findPostsByUserIDAndSortDESC(@Param("id") int id);

    List<Post> findPostsByUserId(int id);
}

