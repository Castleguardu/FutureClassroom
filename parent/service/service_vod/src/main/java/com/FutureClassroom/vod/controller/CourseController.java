package com.FutureClassroom.vod.controller;


import com.FutureClassroom.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.futureClassroom.ftcr.model.vod.Course;
import com.futureClassroom.ftcr.vo.vod.CourseFormVo;
import com.futureClassroom.ftcr.vo.vod.CoursePublishVo;
import com.futureClassroom.ftcr.vo.vod.CourseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.Result;

import java.util.Map;


/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping("/admin/vod/course")
//@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseVo", value = "查询对象", required = false)
            CourseQueryVo courseQueryVo
    ) {
        Page<Course> pageParam = new Page<>(page,limit);
        Map<String,Object> map = courseService.findPage(pageParam,courseQueryVo);
        return Result.ok(map);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CourseFormVo courseFormVo = courseService.getCourseFormVoById(id);
        return  Result.ok(courseFormVo);
    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public Result updateById(@RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseById(courseFormVo);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据课程id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVoByid(
            @ApiParam(value = "课程ID",required = true)
            @PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID",required = true)
            @PathVariable Long id) {
        boolean result = courseService.publishCourseById(id);
        return  Result.ok(null);
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseById(id);
        return Result.ok(null);
    }


}

