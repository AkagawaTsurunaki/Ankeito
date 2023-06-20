package com.github.akagawatsurunaki.ankeito.mapper.qnnre;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    default List<Question> selectByQnnrId(@NonNull String qnnreId) {
        return selectList(Wrappers.<Question>query().lambda().eq(Question::getQnnreId, qnnreId));
    }
}
