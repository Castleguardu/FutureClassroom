package com.FutureClassroom.vod.service.impl;

import com.FutureClassroom.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.futureClassroom.ftcr.model.vod.Chapter;
import com.FutureClassroom.vod.mapper.ChapterMapper;
import com.FutureClassroom.vod.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.model.vod.Video;
import com.futureClassroom.ftcr.vo.vod.ChapterVo;
import com.futureClassroom.ftcr.vo.vod.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-06
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getNestedTreeList(Long courseId) {
        List<ChapterVo> chapterVoList = new ArrayList<>();
//        获取章节信息
        LambdaQueryWrapper<Chapter> queryWrapperChapter = new LambdaQueryWrapper<>();
        queryWrapperChapter.eq(Chapter::getCourseId,courseId);
        queryWrapperChapter.orderByAsc(Chapter::getSort,Chapter::getId);
        List<Chapter> chapterList = baseMapper.selectList(queryWrapperChapter);

//        获取课时信息
        LambdaQueryWrapper<Video> queryWrapperVideo = new LambdaQueryWrapper<>();
        queryWrapperVideo.eq(Video::getCourseId,courseId);
        queryWrapperVideo.orderByAsc(Video::getSort,Video::getId);
        List<Video> videoList = videoService.list(queryWrapperVideo);   //这个地方单独注意下，跟前面的写法稍微不同

        for (int i = 0;i<chapterList.size();i++) {
            Chapter chapter = chapterList.get(i);
//            创建chaptervo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                Video video = videoList.get(i);
                if (chapterVo.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return chapterVoList;
    }

    @Override
    public void removeChapterByCourseId(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
