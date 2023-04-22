package com.FutureClassroom.vod.controller;


import com.FutureClassroom.vod.mapper.TeacherMapper;
import com.FutureClassroom.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.futureClassroom.ftcr.model.vod.Teacher;
import com.futureClassroom.ftcr.vo.vod.TeacherQueryVo;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhw
 * @since 2023-03-26
 */
@Api(tags = "讲师管理接口")
@RestController   //返回json数据
@RequestMapping(value="/admin/vod/teacher")
//@CrossOrigin   //解决跨越问题
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

//    查询所有的讲师
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result findAllTeacher() {
//        做个调用，basemappper的就足够了
        List<Teacher> list = teacherService.list();
        return Result.ok(list);
    }
//    逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)@PathVariable Long id) {
        boolean is = teacherService.removeById(id);
        if (is){
            return Result.ok(null);
        } else{
            return Result.fail(null);
        }
    }
//    进行分页查询
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo){
//        在这里是创建了page对象
        Page<Teacher> pageParam = new Page<>(current,limit);
        if (teacherQueryVo==null) {
//            相当于查询全部
            IPage<Teacher> pageModel = teacherService.page(pageParam);
            return Result.ok(pageModel);
        } else {
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
//            进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
//            这一步主要是处理条件为空的时候
            if (!StringUtils.isNullOrEmpty(name)) {
                wrapper.like("name",name);
            }
            if (level!=null) {  //!StringUtils.isNullOrEmpty(String.valueOf(level))这里不能用这个包装，要不然还是判断可以进来，true
                wrapper.eq("level",level);
            }
            if (!StringUtils.isNullOrEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);
            }
            if (!StringUtils.isNullOrEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);
            }
//            在这里就调用分页查询
            IPage<Teacher> pageModel = teacherService.page(pageParam,wrapper);
            return Result.ok(pageModel);
        }
    }
//    添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("saveTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.save(teacher);
        if (isSuccess) return Result.ok(null);
        else return Result.fail(null);
    }
    //    修改讲师，先写获取讲师id接口
    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }
//    最后修改讲师
    @ApiOperation("修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) return Result.ok(null);
        else return Result.fail(null);
    }
//    批量删除讲师
    @ApiOperation("批量删除讲师")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess) return Result.ok(null);
        else return Result.fail(null);
    }



}

