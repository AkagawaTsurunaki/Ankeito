package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.util.ArrayUtil;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddOptionParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionService {
    OptionMapper optionMapper;

    @Autowired
    OptionService(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    public ServiceResult<List<Option>> addOptions(@NonNull AddOptionParam addOptionParam) {
        try {
            List<Option> options = new ArrayList<>();
            Optional.ofNullable(addOptionParam.getContent()).ifPresentOrElse(
                    contents -> Arrays.stream(contents).forEach(
                            content -> options.add(
                                    Option.builder()
                                            .id(UUID.randomUUID().toString())
                                            .no(ArrayUtil.indexOf(contents, content) + 1)
                                            .content(content)
                                            .questionId(Optional.ofNullable(addOptionParam.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("选项必须依赖于指定的问题")))
                                            .build()
                            )
                    ),
                    () -> {
                        throw new IllegalArgumentException("必须至少有1个选项");
                    }
            );
            options.forEach(optionMapper::insert);
            return ServiceResult.ofOK("成功保存选项", options);
        } catch (IllegalArgumentException e) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e.getMessage());
        }
    }

}
