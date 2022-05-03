package com.atdecap.springbootbackstage.mapper;

import com.atdecap.springbootbackstage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.mapper
 * @create 2022-04-2022/4/1822:26
 * @desc user接口
 **/
//@Mapper
public interface UserMapper extends BaseMapper<User> {//继承BaseMapper类 开启mybatis-plus自动生成代码的准备
/*    @Select("select * from user")
    List<User> findUsers();
    @Insert("insert into user(username,password,email,telephone,address)values (#{username},#{password},#{email},#{telephone},#{address})")
    int insert(User user);

    int update(User user);
@Delete("delete from user where id =#{id}")
    Integer deleteById(@Param("id") Integer id);*/


//这里的selectPage方法跟要写的mybatis-plus重名了 都是继承重写方法
/*@Select("select * from user where integration like  #{integration} limit #{pageNum},#{pageSize}")
//@Select("select * from user where username like #{username} and email like  #{email} and telephone like  #{telephone} limit #{pageNum},#{pageSize}")
    List <User> selectPage(Integer pageNum, Integer pageSize, String integration);
@Select("select count(*) from user")
    int selectPagetotal(String integration);*/
}
