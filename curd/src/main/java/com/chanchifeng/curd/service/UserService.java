package com.chanchifeng.curd.service;

import com.chanchifeng.curd.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User save(User ayUser);
    void delete(Integer id);

    //分页
    Page<User> findAll(Pageable pageable);

    List<User> findByName(String name);
    List<User> findByNameLike(String name);
    List<User> findByIdIn(Collection<Integer> ids);

    Page<User> findAll(int pageNum, int pageSize, User user);
//    Page<User> findAllPage(Specification<User> specification,Pageable pageable);
}
