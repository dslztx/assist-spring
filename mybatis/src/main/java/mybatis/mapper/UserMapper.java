package mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import mybatis.domain.User;

public interface UserMapper {

    User queryUserById(@Param("id") Long userId);

    int insertUser(User user);

}
