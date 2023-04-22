package com.FutureClassroom.vod.service.impl;


import com.FutureClassroom.vod.mapper.CourseDescriptionMapper;
import com.FutureClassroom.vod.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.vod.Course;
import com.futureClassroom.ftcr.model.vod.CourseDescription;
import com.futureClassroom.ftcr.vo.vod.CourseFormVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {



}
