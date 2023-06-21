package com.github.akagawatsurunaki.ankeito.mapper.answer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.akagawatsurunaki.ankeito.entity.answer.ResponseOption;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

import java.util.List;

@Mapper
public interface ResponseOptionMapper extends BaseMapper<ResponseOption> {

    default List<ResponseOption> selectByResponseSheetId(@NonNull String responseSheetId) {
        return selectList(new QueryWrapper<ResponseOption>().lambda().eq(ResponseOption::getResponseSheetId,
                responseSheetId));
    }
}
