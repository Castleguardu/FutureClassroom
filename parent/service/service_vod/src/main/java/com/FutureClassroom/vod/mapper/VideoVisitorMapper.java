package com.FutureClassroom.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.futureClassroom.ftcr.model.vod.VideoVisitor;
import com.futureClassroom.ftcr.vo.vod.VideoVisitorCountVo;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author zhw
 * @since 2023-04-10
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(Long courseId, String startDate, String endDate);
}
