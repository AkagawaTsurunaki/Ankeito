package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QnnreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 问卷实体类
 *
 * @author AkagawaTsurunaki
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qnnre {

    /**
     * 问卷ID
     */
    @TableId(type = IdType.INPUT)
    String id;
    /**
     * 项目ID
     */
    String projectId;
    /**
     * 问卷名称
     */
    String name;
    /**
     * 问卷描述
     */
    String description;
    /**
     * 开始时间
     */
    Date startTime;
    /**
     * 结束时间
     */
    Date stopTime;
    /**
     * 问卷状态
     */
    QnnreStatus qnnreStatus;
}