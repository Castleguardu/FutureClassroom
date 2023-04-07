package com.FutureClassroom.vod.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.futureClassroom.ftcr.model.vod.Course;
import com.futureClassroom.ftcr.vo.vod.CourseFormVo;
import com.futureClassroom.ftcr.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

//    根据id获取课程信息
    CourseFormVo getCourseFormVoById(Long id);

//    获取id后根据id修改课程信息
    void updateCourseById(CourseFormVo courseFormVo);
}
