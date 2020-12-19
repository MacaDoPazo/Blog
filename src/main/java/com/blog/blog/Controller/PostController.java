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
import org.springframework.web.servlet.ModelAndView;

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


}
