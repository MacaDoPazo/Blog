package com.blog.blog.Service;

import com.blog.blog.Model.PostEntity;
import com.blog.blog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
@Transactional
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Override
    public List<PostEntity> listAllPosts() {
            List<PostEntity> ordList = postRepository.findAll();

//        ordList.sort(Collections.reverseOrder(new Comparator<PostEntity>() {
//            public int compare(PostEntity post1, PostEntity post2) {
//                return post1.getCreationDate().compareTo(post2.getCreationDate());
//            }
//        }));
        ordList.sort(new Comparator<PostEntity>() {
            public int compare(PostEntity post1, PostEntity post2) {
                return post1.getCreationDate().compareTo(post2.getCreationDate());
            }
        });
        return ordList;
    }
}
