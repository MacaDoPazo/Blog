package com.blog.blog.Model;
import com.blog.blog.BlogApplication;
import com.blog.blog.Repository.PostRepository;
import com.blog.blog.Service.PostServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PostTest {
@Autowired
PostRepository postRepository;
@Test
    public void savePostInDb() throws Exception {
        PostEntity post = new PostEntity();
        post.setId(1L);
        post.setImage("hola.jpg");
        post.setTitle("hola");
        post.setContent("hola como estas");
        post.setCategory("categoria");
        post.setDeleted(false);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        post.setCreationDate(date);
        postRepository.save(post);
        PostEntity verifyPost = postRepository.getOne(1L);

        assertEquals(post.getId(),verifyPost.getId());

    }
    @Test
    public void searchPostExists()
    {
        PostEntity post = new PostEntity();
        post.setId(1L);
        post.setImage("hola.jpg");
        post.setTitle("hola");
        post.setContent("hola como estas");
        post.setCategory("categoria");
        post.setDeleted(false);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        post.setCreationDate(date);
        postRepository.save(post);
        List<PostEntity> list =postRepository.findByTitleContaining("hola");
        PostEntity postFinded = list.get(0);
        assertEquals(post.getTitle(),postFinded.getTitle());
    }
    @Test
    public void searchPostNotExists()
    {
        PostEntity post = new PostEntity();
        post.setId(1L);
        post.setImage("hola.jpg");
        post.setTitle("hola");
        post.setContent("hola como estas");
        post.setCategory("categoria");
        post.setDeleted(false);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        post.setCreationDate(date);
        postRepository.save(post);
        List<PostEntity> list =postRepository.findByTitleContaining("chau");
        assertTrue(list.isEmpty());
    }

}
