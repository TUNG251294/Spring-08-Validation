package com.example.controller;

import com.example.model.BasicInfo;
import com.example.model.Blog;
import com.example.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

//@GetMapping
//public ModelAndView list(@PageableDefault (value = 3) Pageable pageable,
//                         @RequestParam(value = "search",required = false) String search) {
//    ModelAndView modelAndView = new ModelAndView("list");
//    Page<Blog> blogs;
//    if (search != null) {
//            blogs = blogService.findAllByAuthor(search, pageable);
//            modelAndView.addObject("search", search);
//        } else {
//            blogs = blogService.findAll(pageable);
//        }
//    modelAndView.addObject("blogs", blogs);
//    return modelAndView;
//}

    @GetMapping
    public ModelAndView list(@PageableDefault (value = 5) Pageable pageable,
                             @RequestParam(value = "search", required = false) String search) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Blog> blogs;
        if (search != null) {
            blogs = blogService.queryByName(search, pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("search", search);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }
//data binding khong cần them @ModelAttribute("blog") vào phuong thuc
    @PostMapping("/create")
    public ModelAndView create(@Validated(BasicInfo.class) Blog blog, BindingResult bindingResult) {
        new Blog().validate(blog,bindingResult);
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }else {
            blogService.save(blog);
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("message", "New blog created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("update");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView DeleteForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

//  @PostMapping("/delete")
//    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
//        blogService.remove(blog.getId());
//        return "redirect:/blogs";
//    }
    @PostMapping("/delete")
    public String deleteBlog(Long id) {
        blogService.remove(id);
        return "redirect:/blogs";
    }
//  trong the input trong view phai dat name = id thi moi lay dc id
}
