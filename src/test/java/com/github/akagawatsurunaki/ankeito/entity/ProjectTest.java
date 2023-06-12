package com.github.akagawatsurunaki.ankeito.entity;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@SpringBootTest
public class ProjectTest {

    private static final String BASE_STRING = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!?_";
    private static final String BASE_NAME = "赵钱孙李周吴郑王冯陈楮卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闽席季麻强贾路娄危江童颜郭梅盛林刁锺徐丘骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄麹家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘斜厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍郤璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逑盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐锺离宇文长孙慕容鲜于闾丘司徒司空丌官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷梁晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福";


    /**
     * 随机生成一个项目对象
     *
     * @return 随机生成的项目对象
     */
    private Project genProjectByBuilder() {
        return Project.builder()
                .id(UUID.randomUUID().toString())
                .personInCharge(RandomUtil.randomString(BASE_NAME, 4))
                .projectName(RandomUtil.randomString(BASE_NAME, 8))
                .projectContent(RandomUtil.randomString(BASE_STRING, 32))
                .lastUpdatedBy(RandomUtil.randomString(BASE_NAME, 4))
                .createdBy(RandomUtil.randomString(BASE_NAME, 4))
                .createTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE))
                .lastUpdateDate(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE))
                .build();
    }

    private Project genProjectBySetters() {
        Project project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setPersonInCharge(RandomUtil.randomString(BASE_NAME, 4));
        project.setProjectName(RandomUtil.randomString(BASE_NAME, 8));
        project.setProjectContent(RandomUtil.randomString(BASE_STRING, 32));
        project.setLastUpdatedBy(RandomUtil.randomString(BASE_NAME, 4));
        project.setCreatedBy(RandomUtil.randomString(BASE_NAME, 4));
        project.setCreateTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE));
        project.setLastUpdateDate(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE));
        return project;
    }

    @Test
    public void testEquals() {
        val projectByBuilder = genProjectByBuilder();
        val projectBySetters = genProjectBySetters();

        Assertions.assertNotEquals(projectByBuilder, projectBySetters);

        Assertions.assertNotEquals(
                JSONObject.toJSONString(projectByBuilder),
                JSONObject.toJSONString(projectBySetters)
        );
        Assertions.assertEquals(
                JSONObject.toJSONString(projectByBuilder),
                JSONObject.toJSONString(projectByBuilder)
        );
        Assertions.assertEquals(
                JSONObject.toJSONString(projectBySetters),
                JSONObject.toJSONString(projectBySetters)
        );
    }

    @Test
    public void testEquals2() {
        val projectByBuilder1 = genProjectByBuilder();
        val projectByBuilder2 = Project.builder()
                .id(projectByBuilder1.getId())
                .personInCharge(projectByBuilder1.getPersonInCharge())
                .projectName(projectByBuilder1.getProjectName())
                .projectContent(projectByBuilder1.getProjectContent())
                .createdBy(projectByBuilder1.getCreatedBy())
                .lastUpdatedBy(projectByBuilder1.getLastUpdatedBy())
                .createTime(projectByBuilder1.getCreateTime())
                .lastUpdateDate(projectByBuilder1.getLastUpdateDate())
                .build();

        val projectBySetters1 = genProjectBySetters();
        val projectBySetters2 = new Project();
        projectBySetters2.setId(projectBySetters1.getId());
        projectBySetters2.setPersonInCharge(projectBySetters1.getPersonInCharge());
        projectBySetters2.setProjectName(projectBySetters1.getProjectName());
        projectBySetters2.setProjectContent(projectBySetters1.getProjectContent());
        projectBySetters2.setCreatedBy(projectBySetters1.getCreatedBy());
        projectBySetters2.setLastUpdatedBy(projectBySetters1.getLastUpdatedBy());
        projectBySetters2.setCreateTime(projectBySetters1.getCreateTime());
        projectBySetters2.setLastUpdateDate(projectBySetters1.getLastUpdateDate());

        Assertions.assertNotEquals(projectByBuilder1, projectBySetters1);
        Assertions.assertEquals(projectByBuilder1, projectByBuilder2);
        Assertions.assertEquals(projectBySetters1, projectBySetters2);

        // Equals and JSON serialization testing
        Assertions.assertNotEquals(JSONObject.toJSONString(projectByBuilder1), JSONObject.toJSONString(projectBySetters1));
        Assertions.assertEquals(JSONObject.toJSONString(projectByBuilder1), JSONObject.toJSONString(projectByBuilder1));
        Assertions.assertEquals(JSONObject.toJSONString(projectBySetters1), JSONObject.toJSONString(projectBySetters1));
        Assertions.assertEquals(JSONObject.toJSONString(projectByBuilder1), JSONObject.toJSONString(projectByBuilder2));
        Assertions.assertEquals(JSONObject.toJSONString(projectBySetters1), JSONObject.toJSONString(projectBySetters2));

        // Hash code testing
        val projectSet = new HashSet<Project>();
        projectSet.add(projectByBuilder1);

        // The following lines should pass since we have implemented equals/hashcode
        Assertions.assertTrue(projectSet.contains(projectByBuilder1));
        Assertions.assertTrue(projectSet.contains(projectByBuilder2));
    }


    @Test
    public void testToString() {
        val projectByBuilder = genProjectByBuilder();
        val projectBySetters = genProjectBySetters();
        System.out.println("projectBySetters = " + projectBySetters);
        System.out.println("projectByBuilder = " + projectByBuilder);
    }

}