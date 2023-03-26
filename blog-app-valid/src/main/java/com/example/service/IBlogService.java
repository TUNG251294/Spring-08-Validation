package com.example.service;

import com.example.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBlogService extends IGeneralService<Blog>{

    Page<Blog> findAllByAuthor(String author, Pageable pageable);


    Page<Blog> queryByName(String name, Pageable pageable);
//    Iterable<Blog> findByName(String name);
//    List<Blog> findByName(String name);

}
