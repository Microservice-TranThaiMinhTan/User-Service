package com.qlsv.controller;

import com.qlsv.entity.User;
import com.qlsv.service.UserService;
import com.qlsv.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        return userService.getUserWithDepartment(userId);
    }

//    @Value("${welcome}")
//    private String welcome;
//
//    @GetMapping("/")
//    String helloWorld(){
//        return welcome;
//    }
}