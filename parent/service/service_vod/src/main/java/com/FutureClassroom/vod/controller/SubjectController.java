package com.FutureClassroom.vod.controller;


import com.FutureClassroom.vod.service.SubjectService;
import com.FutureClassroom.vod.service.impl.SubjectServiceImpl;
import com.futureClassroom.ftcr.model.vod.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import result.Result;

import javax.servlet.http.HttpServletResponse;
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
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectService;

//    查询下一层的课程分类
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectList(id);
        return Result.ok(list);
    }
//    导出数据
    @ApiOperation("导出")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }
    //    导入数据
    @ApiOperation("导入")
    @GetMapping("/importData")
//    因为导入文件，java已经有封装好的文件了
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok(null);
    }
}

