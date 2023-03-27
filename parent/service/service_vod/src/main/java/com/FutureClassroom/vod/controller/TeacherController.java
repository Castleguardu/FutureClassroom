package com.FutureClassroom.vod.controller;


import com.FutureClassroom.vod.mapper.TeacherMapper;
import com.FutureClassroom.vod.service.TeacherService;
import com.futureClassroom.ftcr.model.vod.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhw
 * @since 2023-03-26
 */
//@Api(tags = "讲师管理接口")
@RestController   //返回json数据
@RequestMapping(value="/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

//    查询所有的讲师
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher() {
//        做个调用，basemappper的就足够了
        List<Teacher> list = teacherService.list();
        return list;
    }
//    逻辑删除讲师
    @DeleteMapping("remove/{id}")
    public boolean removeTeacher(@PathVariable Long id) {
        boolean is = teacherService.removeById(id);
        return  is;
    }
}

