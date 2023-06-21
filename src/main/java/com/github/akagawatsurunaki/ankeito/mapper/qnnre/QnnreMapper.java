package com.github.akagawatsurunaki.ankeito.mapper.qnnre;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QnnreStatus;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

@Mapper
public interface QnnreMapper extends BaseMapper<Qnnre> {

    default int updateQnnreStatusById(@NonNull String qnnreId, @NonNull QnnreStatus qnnreStatus) {
        return update(null, new UpdateWrapper<Qnnre>().lambda()
                .eq(Qnnre::getId, qnnreId)
                .set(Qnnre::getQnnreStatus, qnnreStatus));
    }
}
