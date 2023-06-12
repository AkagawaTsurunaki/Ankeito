package com.github.akagawatsurunaki.ankeito.entity;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class UserTest {

    private static final String BASE_STRING = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!?_";
    private static final String BASE_NAME = "赵钱孙李周吴郑王冯陈楮卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闽席季麻强贾路娄危江童颜郭梅盛林刁锺徐丘骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄麹家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘斜厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍郤璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逑盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐锺离宇文长孙慕容鲜于闾丘司徒司空丌官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷梁晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福";


    /**
     * 随机生成一个用户对象
     *
     * @return 随机生成的用户对象
     */
    private User genUserByBuilder() {
        return User.builder().id(UUID.randomUUID().toString()).userRole(RandomUtil.randomEle(UserRole.values())).userStatus(RandomUtil.randomEle(UserStatus.values())).startTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE)).stopTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE)).password(RandomUtil.randomString(BASE_STRING, 16)).username(RandomUtil.randomString(BASE_NAME, 4)).lastUpdatedBy(RandomUtil.randomString(BASE_NAME, 4)).lastUpdateTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE)).createdBy(RandomUtil.randomString(BASE_NAME, 4)).creationTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE)).build();
    }

    private User genUserBySetters() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserRole(RandomUtil.randomEle(UserRole.values()));
        user.setUserStatus(RandomUtil.randomEle(UserStatus.values()));
        user.setStartTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE));
        user.setStopTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE));
        user.setPassword(RandomUtil.randomString(BASE_STRING, 16));
        user.setUsername(RandomUtil.randomString(BASE_NAME, 4));
        user.setLastUpdatedBy(RandomUtil.randomString(BASE_NAME, 4));
        user.setLastUpdateTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE));
        user.setCreatedBy(RandomUtil.randomString(BASE_NAME, 4));
        user.setCreationTime(RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE));
        return user;
    }

    @Test
    public void testEquals() {
        val userByBuilder = genUserByBuilder();
        val userBySetters = genUserBySetters();

        Assertions.assertNotEquals(userByBuilder, userBySetters);

        Assertions.assertNotEquals(JSONObject.toJSONString(userByBuilder), JSONObject.toJSONString(userBySetters));
        Assertions.assertEquals(JSONObject.toJSONString(userByBuilder), JSONObject.toJSONString(userByBuilder));
        Assertions.assertEquals(JSONObject.toJSONString(userBySetters), JSONObject.toJSONString(userBySetters));
    }

    @Test
    public void testEquals2() {
        // 测试相同的对象是否相等
        val user1 = genUserByBuilder();
        val user2 = user1;
        Assertions.assertEquals(user1, user2);

        // 测试不同对象之间的相等性
        // 构造两个不同的用户对象
        val user3 = genUserByBuilder();
        val user4 = genUserByBuilder();
        // 用户名不相同，其他都相同
        user4.setUsername(user3.getUsername() + "RANDOM");
        Assertions.assertNotEquals(user3, user4);

        // 测试与null之间的相等性
        Assertions.assertNotEquals(user3, null);

        // 测试与其他类型的对象之间的相等性
        Assertions.assertNotEquals(user3, new Object());

        // 测试用FastJSON序列化后是否相等
        val user5 = genUserByBuilder();
        val user6 = new User();
        user6.setId(user5.getId());
        user6.setUserRole(user5.getUserRole());
        user6.setUserStatus(user5.getUserStatus());
        user6.setStartTime(user5.getStartTime());
        user6.setStopTime(user5.getStopTime());
        user6.setPassword(user5.getPassword());
        user6.setUsername(user5.getUsername());
        user6.setLastUpdatedBy(user5.getLastUpdatedBy());
        user6.setLastUpdateTime(user5.getLastUpdateTime());
        user6.setCreatedBy(user5.getCreatedBy());
        user6.setCreationTime(user5.getCreationTime());
        // user5和user6除了引用不同，其他完全相同
        Assertions.assertEquals(JSONObject.toJSONString(user5), JSONObject.toJSONString(user6));
    }

    @Test
    public void testToString() {
        val userByBuilder = genUserByBuilder();
        val userBySetters = genUserBySetters();
        System.out.println("userBySetters = " + userBySetters);
        System.out.println("userByBuilder = " + userByBuilder);
    }

}
