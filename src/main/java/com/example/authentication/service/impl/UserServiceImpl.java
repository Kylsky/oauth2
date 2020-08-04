package com.example.authentication.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.authentication.model.User;
import com.example.authentication.dao.UserMapper;
import com.example.authentication.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kuancz
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = userMapper.selectList(new EntityWrapper<User>()
                .eq("username",s));
        if (CollectionUtils.isEmpty(users)){
            throw new UsernameNotFoundException("用户名不存在");
        }else if (users.size()>1){
            throw new UsernameNotFoundException("用户名重复，联系管理员");
        }
        User user = users.get(0);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                null);
    }
}
