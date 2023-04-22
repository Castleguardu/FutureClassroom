package com.FutureClassroom.vod.service.impl;


import com.FutureClassroom.vod.mapper.CourseMapper;
import com.FutureClassroom.vod.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.vod.Course;
import com.futureClassroom.ftcr.model.vod.CourseDescription;
import com.futureClassroom.ftcr.model.vod.Subject;
import com.futureClassroom.ftcr.model.vod.Teacher;
import com.futureClassroom.ftcr.vo.vod.CourseFormVo;
import com.futureClassroom.ftcr.vo.vod.CoursePublishVo;
import com.futureClassroom.ftcr.vo.vod.CourseQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;


    @Override
    public Map<String, Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
//        获取条件值，从courseQueryVo中
        String title = courseQueryVo.getTitle();//名称
        Long subjectId = courseQueryVo.getSubjectId();//二级分类
        Long subjectParentId = courseQueryVo.getSubjectParentId();//一级分类
        Long teacherId = courseQueryVo.getTeacherId();//讲师

//        然后进行封装，首先要判断获取到的值是不是空
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id",teacherId);
        }
//        调用方法前进行查询，因为这是在进行分页
        Page<Course> pages = baseMapper.selectPage(pageParam,wrapper);
        long totalCount = pages.getTotal();   //这是总记录数
        long totalPage = pages.getPages();  //这是总页数
        long currentPage = pages.getCurrent();   //这是获得当前页数
        long size = pages.getSize();   //这是获得每页有几个记录数
//        每页数据集合
        List<Course> records = pages.getRecords();
        records.stream().forEach(item->{
            this.getTeacherOrSubjectName(item);
        });
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);
        return map;
    }

    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
//        保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);

//        保存课程基本描述
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        courseDescriptionService.save(courseDescription);

        return course.getId();

    }

    @Override
    public CourseFormVo getCourseFormVoById(Long id) {
//        从Course表中取数据
        Course course = baseMapper.selectById(id);
        if (course==null)   return null;
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        if (courseDescription!=null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }

    @Override
    public void updateCourseById(CourseFormVo courseFormVo) {
        Course course =new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.updateById(course);
        System.out.println("测试进来没");
        System.out.println(courseFormVo.toString());
        CourseDescription courseDescription = courseDescriptionService.getById(course.getId());

        System.out.println(courseDescription);
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return courseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setPublishTime(new Date());
        course.setStatus(1);   //1表示发布，0表示未发布
        return this.updateById(course);
    }

    @Override
    public void removeCourseById(Long id) {   //之前添加过什么，这个地方就删除掉什么
//        根据课程id删除小节
        videoService.removeVideoByCourseId(id);
//        根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
//        根据课程id删除描述
        courseDescriptionService.removeById(id);
//        根据课程id删除课程
        baseMapper.deleteById(id);
    }


    private Course getTeacherOrSubjectName(Course course){
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (teacher!=null){
            course.getParam().put("teacherName",teacher.getName());
        }
//        查询分类一的名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if (subjectOne!=null){
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
        }
    }

