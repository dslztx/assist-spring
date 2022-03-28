package mybatis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import mybatis.domain.User;
import mybatis.mapper.UserMapper;

@Repository
public class UserDao2 {

    @Autowired
    @Qualifier("userMapper#out")
    UserMapper userMapper;

    public User queryUserById(Long id) {
        return userMapper.queryUserById(id);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

}
