package com.FutureClassroom.vod.service.impl;


import com.FutureClassroom.vod.mapper.VideoMapper;
import com.FutureClassroom.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.vod.Video;
import org.springframework.stereotype.Service;

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

}
