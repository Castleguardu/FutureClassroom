package com.FutureClassroom.vod.service;

import com.futureClassroom.ftcr.model.vod.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.futureClassroom.ftcr.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getNestedTreeList(Long courseId);
}
