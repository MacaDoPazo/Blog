package com.blog.blog.Service;

import com.blog.blog.Model.PostEntity;

import java.util.List;

public interface PostService {

    List<PostEntity> listAllPosts();

    List<PostEntity> findPost(String place) throws Exception;
}
