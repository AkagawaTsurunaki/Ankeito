package com.github.akagawatsurunaki.ankeito.mapper.qnnre;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}