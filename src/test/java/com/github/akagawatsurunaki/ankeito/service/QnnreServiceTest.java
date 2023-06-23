package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.util.RandomUtil;
import com.github.akagawatsurunaki.ankeito.api.dto.OptionDTO;
import com.github.akagawatsurunaki.ankeito.api.dto.QnnreDTO;
import com.github.akagawatsurunaki.ankeito.api.dto.QuestionDTO;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryQnnreListParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QnnreStatus;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.Required;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.OptionMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QnnreMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QuestionMapper;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@Rollback()
public class QnnreServiceTest {
    @Mock
    private QnnreMapper qnnreMapper;

    @InjectMocks
    private QnnreService qnnreService;
    @Mock
    private QuestionMapper questionMapper;
    @Mock
    private OptionMapper optionMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetQnnreType_ShouldReturnQnnreTypes() {
        // 调用服务方法
        val result = qnnreService.getQnnreType();

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.OK, result.getCode());
        assertNotNull(result.getData());
    }

    @Test
    public void testAddQnnre_ShouldCreateQnnreSuccessfully() {
        // 模拟参数
        AddQnnreParam addQnnreParam = new AddQnnreParam();
        addQnnreParam.setName("问卷名称");
        addQnnreParam.setProjectId("项目ID");
        addQnnreParam.setDescription("问卷描述");
        addQnnreParam.setStartTime(new Date());

        // 调用服务方法
        ServiceResult<Qnnre> result = qnnreService.addQnnre(addQnnreParam);

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.OK, result.getCode());
        assertNotNull(result.getData());
        assertEquals(addQnnreParam.getName(), result.getData().getName());
        assertEquals(addQnnreParam.getProjectId(), result.getData().getProjectId());
        assertEquals(addQnnreParam.getDescription(), result.getData().getDescription());
    }

    @Test
    public void testAddQnnre_ShouldThrowExceptionIfProjectIdNotSpecified() {
        // 模拟参数，省略projectId
        AddQnnreParam addQnnreParam = new AddQnnreParam();
        // 调用服务方法
        ServiceResult<Qnnre> result = qnnreService.addQnnre(addQnnreParam);

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.ILLEGAL_PARAM, result.getCode());
        Assertions.assertNull(result.getData());
        assertNotNull(result.getMessage());
    }

    @Test
    public void testGetQnnreById_WhenIdExists_ReturnsQnnreObject() {
        // 模拟参数
        QueryQnnreListParam queryQnnreListParam = new QueryQnnreListParam();
        val uuid = UUID.randomUUID().toString();
        queryQnnreListParam.setId(uuid);

        // 模拟QnnreMapper依赖返回的数据
        Qnnre qnnre = new Qnnre();
        when(qnnreMapper.selectById(uuid)).thenReturn(qnnre);

        // 调用服务方法
        ServiceResult<Qnnre> result = qnnreService.getQnnreById(queryQnnreListParam);

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.OK, result.getCode());
        assertNotNull(result.getData());
        assertEquals(qnnre, result.getData());
    }

    @Test
    public void testGetQnnreById_WhenIdNotExists_ReturnsNoSuchEntityError() {
        // 模拟参数，省略设置id
        val queryQnnreListParam = new QueryQnnreListParam();
        // 调用服务方法
        ServiceResult<Qnnre> result = qnnreService.getQnnreById(queryQnnreListParam);

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.NO_SUCH_ENTITY, result.getCode());
        Assertions.assertNull(result.getData());
        assertNotNull(result.getMessage());
    }

    @Test
    public void testGetQnnresExcludeDeletedQnnre_WhenProjectIdExists_ReturnsNonDeletedQnnres() {
        // 模拟参数
        QueryQnnreListParam queryQnnreListParam = new QueryQnnreListParam();
        queryQnnreListParam.setProjectId("项目ID");

        // 模拟QnnreMapper依赖返回的数据
        List<Qnnre> qnnres = new ArrayList<>();
        Qnnre qnnre1 = new Qnnre();
        qnnre1.setQnnreStatus(QnnreStatus.DELETED);
        Qnnre qnnre2 = new Qnnre();
        qnnres.add(qnnre1);
        qnnres.add(qnnre2);
        when(qnnreMapper.selectByProjectId(anyString())).thenReturn(qnnres);

        // 调用服务方法
        ServiceResult<List<Qnnre>> result = qnnreService.getQnnresExcludeDeletedQnnre(queryQnnreListParam);

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.OK, result.getCode());
        assertNotNull(result.getData());
        assertEquals(1, result.getData().size());
        assertEquals(qnnre2, result.getData().get(0));
    }

    @Test
    public void testGetQnnresExcludeDeletedQnnre_WhenProjectIdNotExists_ReturnsIllegalParamError() {
        // 模拟参数，省略设置projectId
        val queryQnnreListParam = new QueryQnnreListParam();
        // 调用服务方法
        ServiceResult<List<Qnnre>> result = qnnreService.getQnnresExcludeDeletedQnnre(queryQnnreListParam);

        // 验证返回结果是否符合预期
        assertEquals(ServiceResultCode.ILLEGAL_PARAM, result.getCode());
        Assertions.assertNull(result.getData());
        assertNotNull(result.getMessage());
    }

    @Test
    public void testPublishQnnre_success() {
        ModifyQnnreParam param = new ModifyQnnreParam();

        val qnnreId = UUID.randomUUID().toString();
        val qnnreStatus = RandomUtil.randomEle(QnnreStatus.values());
        param.setQnnreId(qnnreId);

        when(qnnreMapper.updateQnnreStatusById(qnnreId, qnnreStatus)).thenReturn(1);

        ServiceResult<Object> result = qnnreService.publishQnnre(param);

        assertEquals(ServiceResultCode.OK, result.getCode());
        assertEquals("成功发布问卷", result.getMessage());
    }

    @Test
    public void testPublishQnnre_invalidParam() {
        ModifyQnnreParam param = new ModifyQnnreParam();
        param.setQnnreId(null);

        ServiceResult<Object> result = qnnreService.publishQnnre(param);

        assertEquals(ServiceResultCode.ILLEGAL_PARAM, result.getCode());
        assertNotNull(result.getMessage());
        verify(qnnreMapper, never()).updateQnnreStatusById("", any());
    }

    @Test
    public void testHardDeleteQnnre_success() {
        DeleteQnnreParam param = new DeleteQnnreParam();

        val uuid1 = UUID.randomUUID().toString();

        param.setQnnreId(uuid1);

        Question question = Question.builder().id(2).build();
        Option option = Option.builder().id(3).build();
        QuestionDTO questionDTO = QuestionDTO.builder()
                .question(question)
                .optionList(Collections.singletonList(OptionDTO.builder().option(option).build()))
                .build();

        Qnnre qnnre = Qnnre.builder().id(uuid1).build();
        QnnreDTO qnnreDTO = QnnreDTO.builder()
                .qnnre(qnnre)
                .questionDTOList(Collections.singletonList(questionDTO))
                .build();

        when(qnnreMapper.selectById(uuid1)).thenReturn(qnnre);
        when(questionMapper.deleteById(anyLong())).thenReturn(1);
        when(optionMapper.deleteById(anyLong())).thenReturn(1);

        ServiceResult<QnnreDTO> result = qnnreService.hardDeleteQnnre(param);

        assertEquals(ServiceResultCode.OK, result.getCode());
        assertEquals("问卷删除成功", result.getMessage());
        verify(qnnreMapper, times(1)).deleteById(qnnre.getId());
        verify(questionMapper, times(1)).deleteById(qnnre.getId());
        verify(optionMapper, times(1)).deleteById(qnnre.getId());
    }

    @Test
    public void testHardDeleteQnnre_qnnreNotFound() {
        DeleteQnnreParam param = new DeleteQnnreParam();
        val qnnreId = UUID.randomUUID().toString();
        param.setQnnreId(qnnreId);

        when(qnnreMapper.selectById(qnnreId)).thenReturn(null);

        ServiceResult<QnnreDTO> result = qnnreService.hardDeleteQnnre(param);

        assertEquals(ServiceResultCode.NO_SUCH_ENTITY, result.getCode());
        assertNotNull(result.getMessage());
        verify(qnnreMapper, never()).deleteById(qnnreId);
        verify(questionMapper, never()).deleteById(qnnreId);
        verify(optionMapper, never()).deleteById(qnnreId);
    }

    @Test
    public void testSoftDeleteQnnre_success() {
        DeleteQnnreParam param = new DeleteQnnreParam();
        param.setQnnreId("1");

        Qnnre qnnre = Qnnre.builder().id("1").qnnreStatus(QnnreStatus.DRAFT).build();

        when(qnnreMapper.selectById(anyString())).thenReturn(qnnre);

        ServiceResult<Object> result = qnnreService.softDeleteQnnre(param);

        assertEquals(ServiceResultCode.OK, result.getCode());
        assertEquals("问卷删除成功", result.getMessage());
    }

    @Test
    public void testSoftDeleteQnnre_failedPublished() {
        DeleteQnnreParam param = new DeleteQnnreParam();
        param.setQnnreId("1");

        Qnnre qnnre = Qnnre.builder().id("1").qnnreStatus(QnnreStatus.PUBLISHED).build();

        when(qnnreMapper.selectById(anyString())).thenReturn(qnnre);

        ServiceResult<Object> result = qnnreService.softDeleteQnnre(param);

        assertEquals(ServiceResultCode.FAILED, result.getCode());
        assertEquals("问卷删除失败：不能删除一个正在进行的问卷", result.getMessage());
        verify(qnnreMapper, never()).updateQnnreStatusById(anyString(), any());
    }

    @Test
    public void testSoftDeleteQnnre_notFound() {
        DeleteQnnreParam param = new DeleteQnnreParam();
        param.setQnnreId("1");

        when(qnnreMapper.selectById(anyString())).thenReturn(null);

        ServiceResult<Object> result = qnnreService.softDeleteQnnre(param);

        assertEquals(ServiceResultCode.FAILED, result.getCode());
        assertEquals("内部服务器异常", result.getMessage());
        verify(qnnreMapper, never()).updateQnnreStatusById(anyString(), any());
    }

    @Test
    public void testClearQnnre_success() {
        DeleteQnnreParam param = new DeleteQnnreParam();
        param.setQnnreId("1");

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        Question question1 = Question.builder()
                .id(1)
                .qnnreId("1")
                .content("Question 1")
                .required(Required.REQUIRED)
                .type(QuestionType.MULTIPLE_CHOICE_QUESTION)
                .build();

        QuestionDTO questionDTO1 = QuestionDTO.builder()
                .question(question1)
                .optionList(new ArrayList<>())
                .build();

        Question question2 = Question.builder()
                .id(2)
                .qnnreId("1")
                .content("Question 2")
                .required(Required.OPTIONAL)
                .type(QuestionType.SINGLE_CHOICE_QUESTION)
                .build();

        QuestionDTO questionDTO2 = QuestionDTO.builder()
                .question(question2)
                .optionList(new ArrayList<>())
                .build();

        questionDTOList.add(questionDTO1);
        questionDTOList.add(questionDTO2);

        QnnreDTO qnnreDTO = QnnreDTO.builder()
                .questionDTOList(questionDTOList)
                .build();

        ServiceResult<QnnreDTO> getQnnreResult = ServiceResult.<QnnreDTO>of(ServiceResultCode.OK, "查询成功", qnnreDTO);

        when(qnnreService.get(anyString())).thenReturn(getQnnreResult);
        doNothing().when(questionMapper).deleteById(anyString());
        doNothing().when(optionMapper).deleteById(anyString());

        ServiceResult<QnnreDTO> result = qnnreService.clearQnnre(param);

        assertEquals(ServiceResultCode.OK, result.getCode());
        assertEquals("问卷删除成功", result.getMessage());
        verify(questionMapper, times(2)).deleteById(anyString());
        verify(optionMapper, never()).deleteById(anyString());
    }

    @Test
    public void testClearQnnre_noSuchEntity() {
        DeleteQnnreParam param = new DeleteQnnreParam();
        param.setQnnreId("1");

        ServiceResult<QnnreDTO> getQnnreResult = ServiceResult.<QnnreDTO>of(ServiceResultCode.NO_SUCH_ENTITY, "问卷不存在");

        when(qnnreService.get(anyString())).thenReturn(getQnnreResult);

        ServiceResult<QnnreDTO> result = qnnreService.clearQnnre(param);

        assertEquals(ServiceResultCode.NO_SUCH_ENTITY, result.getCode());
        assertEquals("删除失败, 问卷不存在", result.getMessage());
        verify(questionMapper, never()).deleteById(anyString());
        verify(optionMapper, never()).deleteById(anyString());
    }

}
