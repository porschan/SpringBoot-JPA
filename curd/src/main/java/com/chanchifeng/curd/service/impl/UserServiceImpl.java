package com.chanchifeng.curd.service.impl;

import com.chanchifeng.curd.model.User;
import com.chanchifeng.curd.repository.UserRepository;
import com.chanchifeng.curd.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    @Override
    public Page<User> findAll(int pageNum, int pageSize, User user) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<User> all = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (user.getName() != null && !user.getName().equals("")) {
                    System.out.println("user.getName():" + user.getName());
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + user.getName() + "%"));
                }
                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    System.out.println("user.getPassword():" + user.getPassword());
                    predicates.add(criteriaBuilder.like(root.get("password").as(String.class), "%" + user.getPassword() + "%"));
                }
                Predicate[] pre = new Predicate[predicates.size()];

                System.out.println(predicates.toArray(pre).toString());

                query.where(predicates.toArray(pre));
                return criteriaBuilder.and(predicates.toArray(pre));
            }
        }, pageable);
        return all;
    }
}
