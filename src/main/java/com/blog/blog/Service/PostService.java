package com.blog.blog.Service;

import com.blog.blog.Model.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostEntity> listAllPosts();

    List<PostEntity> findPost(String place) throws Exception;
    PostEntity findPostbyID(Long idPost);
    void updatePost(PostEntity post);
    void deletePost(Long idPost);

}
