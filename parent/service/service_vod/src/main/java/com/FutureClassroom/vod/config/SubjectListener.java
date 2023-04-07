package com.FutureClassroom.vod.config;



import com.FutureClassroom.vod.mapper.SubjectMapper;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.futureClassroom.ftcr.model.vod.Subject;
import com.futureClassroom.ftcr.vo.vod.SubjectEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo,subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
