package com.chanchifeng.curd.service.impl;

import com.chanchifeng.curd.model.User;
import com.chanchifeng.curd.repository.UserRepository;
import com.chanchifeng.curd.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User ayUser) {
        return userRepository.save(ayUser);
    }


    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
        System.out.println("userId:" + id + "用户被删除");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }
    @Override
    public List<User> findByNameLike(String name){
        return userRepository.findByNameLike(name);
    }
    @Override
    public List<User> findByIdIn(Collection<Integer> ids){
        return userRepository.findByIdIn(ids);
    }
}
