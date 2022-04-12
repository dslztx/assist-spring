package me.mybatis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.mybatis.domain.User;
import me.mybatis.mapper.UserMapper;

@Repository
public class UserDao {

    @Autowired
    @Qualifier("userMapper#in")
    UserMapper userMapper;

    public User queryUserById(Long id) {
        return userMapper.queryUserById(id);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

}
