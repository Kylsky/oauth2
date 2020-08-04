package com.example.authentication.service;

import com.example.authentication.model.User;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author kuancz
 */
public interface UserService extends IService<User>, UserDetailsService {

}
