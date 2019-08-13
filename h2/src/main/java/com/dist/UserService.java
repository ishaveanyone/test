package com.dist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-13 10:29
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getOneById(Long id){
        return Optional.ofNullable(userRepository.findById(id)).get().orElse(null);
    }
}
