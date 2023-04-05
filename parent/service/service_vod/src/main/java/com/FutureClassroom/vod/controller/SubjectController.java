package com.FutureClassroom.vod.controller;


import com.FutureClassroom.vod.service.SubjectService;
import com.FutureClassroom.vod.service.impl.SubjectServiceImpl;
import com.futureClassroom.ftcr.model.vod.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.Result;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zhw
 * @since 2023-04-04
 */
@Api(tags = "课程")
@RestController
@RequestMapping("/admin/vod/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectService;

//    查询下一层的课程分类
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildrenSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectList(id);
        return Result.ok(list);
    }
}

