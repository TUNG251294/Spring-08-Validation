package com.example.service;

import com.example.model.Blog;
import com.example.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BlogService implements IBlogService{
    @Autowired
    private IBlogRepository blogRepository;



    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }


    @Override
    public Page<Blog> findAllByAuthor(String author, Pageable pageable) {
        return blogRepository.findAllByAuthor(author, pageable);
    }

    @Override
    public Page<Blog> queryByName(String name, Pageable pageable) {
        return blogRepository.queryByName(name, pageable);
    }

//    @Override
//    public List<Blog> findByName(String name) {
//        Optional<List<Blog>> blogs = blogRepository.findByName(name);
//        List<Blog> blogList = blogs.orElseThrow(() -> new IllegalArgumentException("SAI NAME ROI"));
//                return blogList;
//    }

//    @Override
//    public Iterable<Blog> findByName(String name) {
//        return blogRepository.findByName(name);
//    }


}
