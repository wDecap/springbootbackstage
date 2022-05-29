//package com.atdecap.springbootbackstage.controller;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.date.Quarter;
//import com.atdecap.springbootbackstage.common.Result;
//import com.atdecap.springbootbackstage.entity.User;
//import com.atdecap.springbootbackstage.service.IUserService;
//import com.atdecap.springbootbackstage.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author Decap
// * @BelongsProject: IntelliJ IDEA
// * @BelongsPackage: com.atdecap.springbootbackstage.controller
// * @create 2022-05-2022/5/238:49
// * @desc
// **/
//@RestController
//@RequestMapping("/echarts")
//
//public class EchartsController {
//    @Autowired
//    private IUserService userService;
//
//    @GetMapping("/example")
//     public Result get(){
//         HashMap<Object, Object> map = new HashMap<>();
//         map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
//         map.put("y1",CollUtil.newArrayList(120, 132, 101, 134, 90, 230, 210));
//         map.put("y2",CollUtil.newArrayList(220, 182, 191, 234, 290, 330, 310));
//         map.put("y3",CollUtil.newArrayList(150, 232, 201, 154, 190, 330, 410));
//         map.put("y4",CollUtil.newArrayList(320, 332, 301, 334, 390, 330, 320));
//         map.put("y5",CollUtil.newArrayList(820, 932, 901, 934, 1290, 1330, 1320));
//
//         return Result.success(map);
//     }
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
//}
//
