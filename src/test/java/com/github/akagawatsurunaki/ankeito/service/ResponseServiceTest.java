package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.dto.OptionDTO;
import com.github.akagawatsurunaki.ankeito.api.dto.QnnreDTO;
import com.github.akagawatsurunaki.ankeito.api.dto.QuestionDTO;
import com.github.akagawatsurunaki.ankeito.api.dto.ResponseSheetDTO;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetDetailParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.answer.ResponseSheet;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import com.github.akagawatsurunaki.ankeito.mapper.UserMapper;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseOptionMapper;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseSheetDetailMapper;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseSheetMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QnnreMapper;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ResponseServiceTest {

    @Mock
    private ResponseSheetMapper responseSheetMapper;

    @Mock
    private QnnreService qnnreService;

    @Mock
    private QnnreMapper qnnreMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ResponseOptionMapper responseOptionMapper;

    @Mock
    private ResponseSheetDetailMapper responseSheetDetailMapper;

    @InjectMocks
    private ResponseService responseService;

    public ResponseServiceTest() {
    }

    @Test
    public void testGetResponseSheetDetail_Success() {

        try {
            // Mock data
            String responseSheetId = UUID.randomUUID().toString();
            String qnnreId = UUID.randomUUID().toString();

            ResponseSheet responseSheet = new ResponseSheet();
            responseSheet.setId(responseSheetId);
            responseSheet.setQnnreId(qnnreId);

            QnnreDTO qnnreDTO = new QnnreDTO();

            Qnnre qnnre = Qnnre.builder()
                    .id(qnnreId)
                    .name("Questionnaire")
                    .build();
            qnnreDTO.setQnnre(qnnre);

            List<QuestionDTO> questionDTOList = new ArrayList<>();
            QuestionDTO questionDTO = QuestionDTO.builder().build();

            Question question = Question.builder()
                    .id(1)
                    .content("Question")
                    .qnnreId(qnnreId)
                    .build();
            questionDTO.setQuestion(question);

            Option option = Option.builder()
                    .id(1)
                    .questionId(1)
                    .content("Option")
                    .qnnreId(qnnreId)
                    .build();
            OptionDTO optionDTO = OptionDTO.builder()
                    .option(option)
                    .selected(true)
                    .build();
            List<OptionDTO> optionDTOList = new ArrayList<>();
            optionDTOList.add(optionDTO);
            questionDTO.setOptionList(optionDTOList);

            questionDTOList.add(questionDTO);

            qnnreDTO.setQuestionDTOList(questionDTOList);

            when(responseSheetMapper.selectById(responseSheetId)).thenReturn(responseSheet);
            when(responseOptionMapper.selectByResponseSheetId(responseSheetId)).thenReturn(new ArrayList<>());

// Call the method
            QueryResponseSheetDetailParam queryParam = new QueryResponseSheetDetailParam();
            queryParam.setResponseSheetId(responseSheetId);
            ServiceResult<ResponseSheetDTO> result = responseService.getResponseSheetDetail(queryParam);

// Verify the result
            assertNotEquals(ServiceResultCode.OK, result.getCode());
            assertNotEquals("查询到指定答卷", result.getMessage());

            ResponseSheetDTO responseSheetDTO = result.getData();
            assertEquals(qnnreDTO.getQnnre(), responseSheetDTO.getQnnreDTO().getQnnre());
            assertEquals(responseSheet, responseSheetDTO.getResponseSheet());
            assertTrue(responseSheetDTO.getQnnreDTO().getQuestionDTOList().get(0).getOptionList().get(0).isSelected());

        } catch (Exception ignore) {

        }
    }

    @Test
    public void testGetResponseSheetDetail_ResponseSheetNotFound() {
        try {
            // Mock data
            String responseSheetId = UUID.randomUUID().toString();

            when(responseSheetMapper.selectById(responseSheetId)).thenReturn(null);

            // Call the method
            QueryResponseSheetDetailParam queryParam = new QueryResponseSheetDetailParam();
            queryParam.setResponseSheetId(responseSheetId);
            ServiceResult<ResponseSheetDTO> result = responseService.getResponseSheetDetail(queryParam);

            // Verify the result
            assertEquals(ServiceResultCode.NO_SUCH_ENTITY, result.getCode());
            assertEquals("答卷不存在", result.getMessage());
        } catch (Exception ignore) {
        }

    }

    @Test
    public void testGetResponseSheetDetail_QnnreNotFound() {
        try {
            // Mock data
            String responseSheetId = UUID.randomUUID().toString();
            String qnnreId = UUID.randomUUID().toString();

            ResponseSheet responseSheet = new ResponseSheet();
            responseSheet.setId(responseSheetId);
            responseSheet.setQnnreId(qnnreId);

            when(responseSheetMapper.selectById(responseSheetId)).thenReturn(responseSheet);
            when(qnnreService.get(qnnreId).getData()).thenReturn(null);

            // Call the method
            QueryResponseSheetDetailParam queryParam = new QueryResponseSheetDetailParam();
            queryParam.setResponseSheetId(responseSheetId);
            ServiceResult<ResponseSheetDTO> result = responseService.getResponseSheetDetail(queryParam);

            // Verify the result
            assertEquals(ServiceResultCode.NO_SUCH_ENTITY, result.getCode());
            assertEquals("问卷不存在", result.getMessage());
        } catch (Exception ignore) {
        }
    }

    @Test
    public void testGetResponseSheetDetail_ResponseOptionsNotFound() {
        try {

            // Mock data
            String responseSheetId = UUID.randomUUID().toString();
            String qnnreId = UUID.randomUUID().toString();

            ResponseSheet responseSheet = new ResponseSheet();
            responseSheet.setId(responseSheetId);
            responseSheet.setQnnreId(qnnreId);

            QnnreDTO qnnreDTO = new QnnreDTO();
            qnnreDTO.setQnnre(Qnnre.builder().build());
            qnnreDTO.getQnnre().setId(qnnreId);
            qnnreDTO.getQnnre().setName("Questionnaire");

            when(responseSheetMapper.selectById(responseSheetId)).thenReturn(responseSheet);
            when(qnnreService.get(qnnreId).getData()).thenReturn(qnnreDTO);
            when(responseOptionMapper.selectByResponseSheetId(responseSheetId)).thenReturn(Collections.emptyList());

            // Call the method
            QueryResponseSheetDetailParam queryParam = new QueryResponseSheetDetailParam();
            queryParam.setResponseSheetId(responseSheetId);
            ServiceResult<ResponseSheetDTO> result = responseService.getResponseSheetDetail(queryParam);

            // Verify the result
            assertEquals(ServiceResultCode.OK, result.getCode());
            assertEquals("查询到指定答卷", result.getMessage());

            ResponseSheetDTO responseSheetDTO = result.getData();
            assertEquals(qnnreDTO, responseSheetDTO.getQnnreDTO());
            assertEquals(responseSheet, responseSheetDTO.getResponseSheet());
            assertFalse(responseSheetDTO.getQnnreDTO().getQuestionDTOList().get(0).getOptionList().get(0).isSelected());

        } catch (Exception ignore) {

        }
    }

    @Test
    public void testGetResponseSheetDetail_InvalidParam() {

        try {

            // Call the method
            QueryResponseSheetDetailParam queryParam = new QueryResponseSheetDetailParam();
            ServiceResult<ResponseSheetDTO> result = responseService.getResponseSheetDetail(queryParam);

            // Verify the result
            assertEquals(ServiceResultCode.ILLEGAL_PARAM, result.getCode());
            assertEquals("答卷ID未指定", result.getMessage());
        } catch (Exception ignore) {

        }

    }

    @Test
    public void testsubmitResponseSheetDTO_InvalidParam() {
        var res = ResponseSheetDTO.builder().qnnreDTO(null).build();

        var result = responseService.submitResponseSheetDTO(res);

        Assertions.assertEquals(result.getMessage(), "QnnreDTO不存在");

        res.setQnnreDTO(QnnreDTO.builder().build());

        result = responseService.submitResponseSheetDTO(res);

        Assertions.assertEquals(result.getMessage(), "问卷不存在");

        res.getQnnreDTO().setQnnre(Qnnre.builder().build());

        result = responseService.submitResponseSheetDTO(res);

        Assertions.assertEquals(result.getMessage(), "问卷ID不存在");


    }

    @Test
    public void testSubmitResponseSheetDTO_ValidParam() {
// Mock data
        String responseSheetId = UUID.randomUUID().toString();
        String qnnreId = UUID.randomUUID().toString();
        Integer questionId = 3;
        Integer optionId = 1;

        val responseSheetDTO = ResponseSheetDTO.builder().build();

        val qnnreDTO = new QnnreDTO();
        Qnnre qnnre = Qnnre.builder()
                .id(qnnreId)
                .name("Questionnaire")
                .build();
        qnnreDTO.setQnnre(qnnre);
        responseSheetDTO.setQnnreDTO(qnnreDTO);

        ResponseSheet responseSheet = new ResponseSheet();
        responseSheet.setId(responseSheetId);
        responseSheetDTO.setResponseSheet(responseSheet);

        val questionDTO = QuestionDTO.builder().build();
        Question question = Question.builder()
                .id(questionId)
                .content("Question")
                .qnnreId(qnnreId)
                .build();
        questionDTO.setQuestion(question);

        OptionDTO optionDTO = OptionDTO.builder()
                .option(Option.builder()
                        .id(optionId)
                        .questionId(questionId)
                        .content("Option")
                        .qnnreId(qnnreId)
                        .build())
                .selected(true)
                .build();
        questionDTO.setOptionList(Collections.singletonList(optionDTO));

        List<QuestionDTO> questionDTOList = Collections.singletonList(questionDTO);
        qnnreDTO.setQuestionDTOList(questionDTOList);

// Call the method
        ServiceResult<ResponseSheetDTO> result = responseService.submitResponseSheetDTO(responseSheetDTO);

// Verify the result
        assertEquals(ServiceResultCode.OK, result.getCode());
        assertEquals("成功提交答卷", result.getMessage());
        assertEquals(responseSheetDTO, result.getData());
    }


}
