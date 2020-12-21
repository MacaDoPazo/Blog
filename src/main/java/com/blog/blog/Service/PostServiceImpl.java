package com.blog.blog.Service;

import com.blog.blog.Model.PostEntity;
import com.blog.blog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<PostEntity> findPost(String place) throws Exception{
           List<PostEntity> listPosts = postRepository.findByTitleContaining(place);
           if(listPosts.isEmpty())
           {
               throw new Exception("El post no existe");
           }
           else {
               return listPosts;
           }
    }

    @Override
    public PostEntity findPostbyID(Long idPost) throws Exception{
        PostEntity post= postRepository.getOne(idPost);
        if(post == null)
        {
            throw new Exception("El post no existe");
        }
        else {
            return post;
        }
    }

    @Override
    public void updatePost(PostEntity post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long idPost) {
        postRepository.deleteById(idPost);
    }


}
