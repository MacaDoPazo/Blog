package com.blog.blog.Controller;

import com.blog.blog.Model.PostEntity;
import com.blog.blog.Service.PostService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;


    @RequestMapping("home")
    public ModelAndView index()
    {
        List<PostEntity> postEntities = postService.listAllPosts();
         ModelMap model = new ModelMap();
        model.put("listPost",postEntities);
        return new ModelAndView("index.html",model);
    }
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public ModelAndView searchPost(@RequestParam("search") String search)
    {
        try {
            List<PostEntity> listPost = postService.findPost(search);
            ModelMap model = new ModelMap();
            model.put("listPost",listPost);
            return new ModelAndView("search",model);
        }
        catch (Exception e)
        {

            List<PostEntity> postEntities = postService.listAllPosts();
            ModelMap model = new ModelMap();
            model.put("listPost",postEntities);
            model.put("error",e.getMessage());
            return new ModelAndView("index.html",model);
        }

    }
    @RequestMapping("newPost")
    public ModelAndView newPost()
    {
        return new ModelAndView("newPost.html");
    }
    @RequestMapping("create")
    public ModelAndView create(@RequestParam("title")String title,
                               @RequestParam("content") String content)
    // @RequestParam("file") MultipartFile file)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        PostEntity post = new PostEntity();
        post.setTitle(title);
        post.setContent(content);
        post.setCreationDate(date);
        postService.updatePost(post);

        return new ModelAndView("redirect:/home");
    }
    @RequestMapping("modify")
    public ModelAndView modify(@RequestParam("idPost") Long idPost)
    {
        PostEntity post = postService.findPostbyID(idPost);
        ModelMap model = new ModelMap();
        model.put("post",post);
        return new ModelAndView("modify.html",model);
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("idPost") Long idPost, @RequestParam("title")String title,
                               @RequestParam("content") String content)
                              // @RequestParam("file") MultipartFile file)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        PostEntity post = new PostEntity();
        post.setId(idPost);
        post.setTitle(title);
        post.setContent(content);
        post.setCreationDate(date);
        postService.updatePost(post);
        return new ModelAndView("redirect:/home");
    }
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("idPost") Long idPost)
    {
        postService.deletePost(idPost);
        return new ModelAndView("redirect:/home");
    }


}
