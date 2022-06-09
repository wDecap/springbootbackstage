package com.atdecap.springbootbackstage.controller;

//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.date.Quarter;
//import cn.hutool.core.lang.TypeReference;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.json.JSONUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.atdecap.springbootbackstage.common.Constants;
import com.atdecap.springbootbackstage.common.Result;
import com.atdecap.springbootbackstage.config.AuthAccess;
import com.atdecap.springbootbackstage.entity.Files;
//import com.atdecap.springbootbackstage.entity.User;
import com.atdecap.springbootbackstage.mapper.FileMapper;
//import com.atdecap.springbootbackstage.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
//import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
//
/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.controller
 * @create 2022-05-2022/5/23 8:49
 * @desc
 **/
//@RestController
//@RequestMapping("/echarts")
////
//public class EchartsController {
////    @Autowired
////    private IUserService userService;
////
////    @GetMapping("/example")
////     public Result get(){
////         HashMap<Object, Object> map = new HashMap<>();
////         map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
////         map.put("y1",CollUtil.newArrayList(120, 132, 101, 134, 90, 230, 210));
////         map.put("y2",CollUtil.newArrayList(220, 182, 191, 234, 290, 330, 310));
////         map.put("y3",CollUtil.newArrayList(150, 232, 201, 154, 190, 330, 410));
////         map.put("y4",CollUtil.newArrayList(320, 332, 301, 334, 390, 330, 320));
////         map.put("y5",CollUtil.newArrayList(820, 932, 901, 934, 1290, 1330, 1320));
////
////         return Result.success(map);
////     }
////    @GetMapping("/members")
////    public Result members() {
////        List<User> list = userService.list();
////        int q1 = 0; // 第一季度
////        int q2 = 0; // 第二季度
////        int q3 = 0; // 第三季度
////        int q4 = 0; // 第四季度
////        for (User user : list) {
////            Date createTime = user.getCreateTime();
////            Quarter quarter = DateUtil.quarterEnum(createTime);
////            switch (quarter) {
////                case Q1: q1 += 1; break;
////                case Q2: q2 += 1; break;
////                case Q3: q3 += 1; break;
////                case Q4: q4 += 1; break;
////                default: break;
////            }
////        }
////        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
////    }
////
////}
////
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    //
//    @Autowired
//    private IUserService userService;
//
    @Resource
    private FileMapper fileMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


//    @GetMapping("/example")
//    public Result get() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
//        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
//        return Result.success(map);
//    }
//
//    @GetMapping("/members")
//    public Result members() {
//        List<User> list = userService.list();
//        int q1 = 0; // 第一季度
//        int q2 = 0; // 第二季度
//        int q3 = 0; // 第三季度
//        int q4 = 0; // 第四季度
//        for (User user : list) {
//            Date createTime = user.getCreateTime();
//            Quarter quarter = DateUtil.quarterEnum(createTime);
//            switch (quarter) {
//                case Q1: q1 += 1; break;
//                case Q2: q2 += 1; break;
//                case Q3: q3 += 1; break;
//                case Q4: q4 += 1; break;
//                default: break;
//            }
//        }
//        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
//    }
//
    @AuthAccess
    @GetMapping("/file/front/all")
//    @Cacheable(value = "files" ,key = "'frontAll'")
    public Result frontAll() {
        // 1. 从缓存获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.FILES_KEY);
        List<Files> files;
        if (StrUtil.isBlank(jsonStr)) {  // 2. 取出来的json是空的
            files = fileMapper.selectList(null);  // 3. 从数据库取出数据
            // 4. 再去缓存到redis
            stringRedisTemplate.opsForValue().set(Constants.FILES_KEY, JSONUtil.toJsonStr(files));
        } else {
            // 减轻数据库的压力
            // 5. 如果有, 从redis缓存中获取数据
            files = JSONUtil.toBean(jsonStr, new TypeReference<List<Files>>() {

            }, true);//hutool的toBean方法把json转成一个泛型类 true忽略转化不了的数据
        }

//        return Result.success(fileMapper.selectList(null));
        return Result.success(files);
   }
//
    }
