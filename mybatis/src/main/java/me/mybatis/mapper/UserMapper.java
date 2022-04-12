package me.mybatis.mapper;

import me.mybatis.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User queryUserById(@Param("id") Long userId);

    int insertUser(User user);

}
