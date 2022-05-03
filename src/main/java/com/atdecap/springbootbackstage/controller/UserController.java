package com.atdecap.springbootbackstage.controller;

import com.atdecap.springbootbackstage.entity.User;
import com.atdecap.springbootbackstage.mapper.UserMapper;
import com.atdecap.springbootbackstage.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.controller
 * @create 2022-04-2022/4/1823:00
 * @desc
 **/
@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    @Resource
//    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //查询所用数据
    @GetMapping("/")
    public List<User> index() {
//        List<User> users = userMapper.findUsers();
//        return users;
        return userService.list();
    }

    @DeleteMapping("/{id}")
//    public Integer deleteUser(@PathVariable Integer id) {
//        return userMapper.deleteById(id);  //mybatis的方法（UserMapper.java里的deleteById）
    public boolean deleteUser(@PathVariable Integer id) {
        return userService.removeById(id);    //用的是mybatis-plus的serviceimpl方法
    }
    //批量删除接口
    @PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
   return userService.removeBatchByIds(ids);
}
    /**
     * mybatis的方式
     * @param pageNum
     * @param pageSize
     * @param integration
     * @return
     * 分页查询
     * @RequestParam接收参数
     * limit第一个参数 = (pageNum-1) * pageSize
     */

  /*  @GetMapping("/page") //接口路径： /user/page
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String integration
    ) {
        pageNum = (pageNum - 1) * pageSize;

*//*也可以用mysql全文检索，用mybaits写动态 ，觉得太繁琐不想用*//*
        integration = integration.trim();//除去两头空格
        String[] s = integration.split(" "); //按照空格进行分割
        System.out.println(Arrays.toString(s)); //打印方便看拼接
        integration = "";//初始化
        for (String s1 : s) {
            integration += "%" + s1 + "%";//拼接sql
        }
        System.out.println(integration);
        List<User> data = userMapper.selectPage(pageNum, pageSize, integration);
        Integer total = userMapper.selectPagetotal(integration);
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
        }
*/
    /**
     * mybatis-plus的方法
     * @param pageNum
     * @param pageSize
     * @param integration
     * @return
     */
//mybatis-plus整合搜索功能
    @GetMapping("/page") //接口路径： /user/page
    public IPage<User> findPage(@RequestParam Integer pageNum,
                          @RequestParam Integer pageSize,
                          @RequestParam(defaultValue="") String integration)
     {
         IPage<User> page = new Page<>(pageNum, pageSize);
         QueryWrapper<User> queryWrapper = new QueryWrapper<>();
         integration = integration.trim();//除去两头空格
         String[] s = integration.split(" "); //按照空格进行分割
         System.out.println(Arrays.toString(s)); //打印方便看拼接
         integration = "";//初始化
         for (String s1 : s) {
             integration += "%" + s1 + "%";//拼接sql
         }
         if (!"".equals("integration")) {
             queryWrapper.like("integration",integration);
         }
//         queryWrapper.orderByDesc("id");
         /*小知识点
         1.如果要加条件联合查询 like其实框架已经加了and  queryWrapper.like() 相当于 queryWrapper.and(w -> w.like())
         2. queryWrapper.or().like()
         */
         return userService.page(page,queryWrapper);
    }
}
