package com.FutureClassroom.vod.service.impl;


import com.FutureClassroom.vod.mapper.VideoMapper;
import com.FutureClassroom.vod.service.VideoService;
import com.FutureClassroom.vod.service.VodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.vod.Video;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VodService vodService;
//    根据课程id删除小节
    @Override
    public void removeVideoByCourseId(Long id) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();   //为什么要专门写个wrapper，
        // 因为它不是根据主键进行删除就要专门写个wrapper
        wrapper.eq("course_id",id);
        List<Video> videoList = baseMapper.selectList(wrapper);
//        遍历获取每个小节的视频id
        for(Video video:videoList) {
            String videoSourceId = video.getVideoSourceId();
            //如果视频id不为空，调用方法删除
            if (!StringUtils.isNullOrEmpty(videoSourceId)) {
                vodService.removeVideo(videoSourceId);
            }
        }

        baseMapper.delete(wrapper);
    }

    @Override
    public void removeVideoById(Long id) {
        //1 删除视频
        Video video = baseMapper.selectById(id);
        //获取视频id
        String videoSourceId = video.getVideoSourceId();
        //如果视频id不为空，调用方法删除
        if(!StringUtils.isNullOrEmpty(videoSourceId)) {
            vodService.removeVideo(videoSourceId);
        }
        //2 删除小节
        baseMapper.deleteById(id);
    }
}
