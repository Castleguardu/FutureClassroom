package com.FutureClassroom.vod.service;

import com.futureClassroom.ftcr.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zhw
 * @since 2023-04-04
 */
public interface SubjectService extends IService<Subject> {

//    查询下一课程分类
    List<Subject> selectList(Long id);
    /**
    导出课程
    @param response
    */
    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
