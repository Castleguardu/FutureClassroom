package com.FutureClassroom.vod.service.impl;


import com.FutureClassroom.vod.mapper.VideoVisitorMapper;
import com.FutureClassroom.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.vod.VideoVisitor;
import com.futureClassroom.ftcr.vo.vod.VideoVisitorCountVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-10
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        List<VideoVisitorCountVo> videoVisitorCountVoList = baseMapper.findCount(courseId,startDate,endDate);
        Map<String,Object> map = new HashMap<>();
        List<String> dateList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getJoinTime)
                .collect(Collectors.toList());
        List<Integer> countList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());

        map.put("xData",dateList);
        map.put("yData",countList);
        return map;
    }
}
