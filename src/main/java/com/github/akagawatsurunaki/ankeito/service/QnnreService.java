package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QnnreType;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QnnreService {
    public ServiceResult<List<String>> getQnnreType() {
        val data = Arrays.stream(QnnreType.values())
                .map(it -> it.value).toList();
        return ServiceResult.ofOK("查询到" + data.size() + "项目类型", data);
    }
}
