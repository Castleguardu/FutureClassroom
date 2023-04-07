package com.FutureClassroom.vod.service.impl;

import com.FutureClassroom.vod.config.SubjectListener;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.futureClassroom.ftcr.model.vod.Subject;
import com.FutureClassroom.vod.mapper.SubjectMapper;
import com.FutureClassroom.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futureClassroom.ftcr.vo.vod.SubjectEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zhw
 * @since 2023-04-04
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;
//    查询下一层课程的分类
    public List<Subject> selectList(Long id) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);
//        向list集合每个subject对象中设置hasChildren
        for (Subject subject:subjectList) {
            Long subjectId = subject.getId();
            boolean isChild = this.isChildren(subjectId);
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }
//    导出数据，用excel
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Subject> dictList = baseMapper.selectList(null);
            List<SubjectEeVo> dictVoList = new ArrayList<>(dictList.size());
            for (Subject subject:dictList){
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject,subjectEeVo);
                dictVoList.add(subjectEeVo);
            }
            EasyExcel.write(response.getOutputStream(),SubjectEeVo.class).sheet("课程分类").doWrite(dictVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),SubjectEeVo.class,subjectListener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isChildren(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count>0;
    }
}
