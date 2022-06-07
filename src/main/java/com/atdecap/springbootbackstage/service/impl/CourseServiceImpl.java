package com.atdecap.springbootbackstage.service.impl;

import com.atdecap.springbootbackstage.entity.Course;
import com.atdecap.springbootbackstage.mapper.CourseMapper;
import com.atdecap.springbootbackstage.service.ICourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Decap
 * @since 2022-06-01
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Page<Course> findPage(Page<Course> page, String name) {
        return courseMapper.findPage(page, name);
    }

    @Transactional //事务处理 保证MySQL的原子性
    @Override
    public void setStudentCourse(Integer courseId, Integer studentId) {
        courseMapper.deleteStudentCourse(courseId, studentId);
        courseMapper.setStudentCourse(courseId, studentId);
    }

}
