package com.chanchifeng.curd.controller;

import com.chanchifeng.curd.model.User;
import com.chanchifeng.curd.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/curdController")
public class CurdController {

    @Resource
    private UserService userService;

    @GetMapping("/queryAll")
    public String queryAll(Model model) {
        ModelAndView modelAndView = new ModelAndView("index");

        List<User> userList =  userService.findAll();
        System.out.println(userList.size() + "!!");
//        modelAndView.add

        model.addAttribute("users",userList);

        return "curd/index";
    }

    @RequestMapping("/delete/{userId}")
    public String delete(Model model,@PathVariable("userId") Integer userId){

        //删除

        userService.delete(userId);

        return "redirect:/curdController/queryAll";
    }

    @RequestMapping(value = "/add")
    public String add(User user) {
//        employeeDao.save(employee);

        System.out.println(user.getId() + "!!");

        User save = userService.save(user);

        System.out.println("new ? id :" + save.getId());

        return "redirect:/curdController/queryAll";
    }

    @RequestMapping("/queryById/{userId}")
    public String queryById(Model model,@PathVariable("userId") Integer userId){

        System.out.println("queryById!!");

        model.addAttribute("user",userService.findById(userId));

        return "curd/update";
    }

    @RequestMapping(value = "/update")
    public String update(User user) {

        System.out.println(user.getId() + "!!");

        userService.save(user);

        return "redirect:/curdController/queryAll";
    }

    /* START 模糊搜索 + 分页*/

    @RequestMapping(value="/findAllJSON")
    @ResponseBody
    public Page<User> findAllJson(User user, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        System.out.println();
        Page<User> all = userService.findAll(page, limit, user);

        return all;
    }
    /* END 模糊搜索 + 分页*/

}
