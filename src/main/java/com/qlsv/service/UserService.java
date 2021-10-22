package com.qlsv.service;

import com.qlsv.entity.Department;
import com.qlsv.entity.User;
import com.qlsv.repository.UserReposity;
import com.qlsv.vo.ResponseTemplateVO;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private UserReposity userReposity;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userReposity.save(user);
    }

    @Retry(name = "basic")
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userReposity.findById(userId).get();
        vo.setUser(user);
        Department department = restTemplate.getForObject("http://localhost:9001/department/"
                                + user.getDepartmentId(),
                        Department.class);

        vo.setDepartment(department);

        return vo;
    }
}
