package com.FutureClassroom.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.futureClassroom.ftcr.model.vod.Course;
import com.futureClassroom.ftcr.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVoById(Long id);
}
