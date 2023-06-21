let questionnaireTitle
let questionnaireDescription
let questionnaireId
const problem = []
let responseSheetDTO

onload = () => {
    let queryResponseSheetParam = {
        responseSheetId: $util.getPageParam('selectedResponseSheetId')
    }

    $.ajax({
        url: '/response/getResponseSheetDetail', // 接口地址
        type: 'POST',
        data: JSON.stringify(queryResponseSheetParam),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === '666') {
                responseSheetDTO = {
                    responseSheet: res.data.responseSheet,
                    qnnreDTO: res.data.qnnreDTO
                }
                responseSheetDTO.responseSheet = {
                    id: res.data.responseSheet.id,
                    qnnreId: res.data.responseSheet.qnnreId,
                    qnnreName: res.data.responseSheet.qnnreName,
                    respondentId: res.data.responseSheet.respondentId,
                    respondentName: res.data.responseSheet.respondentName,
                    finishedTime: res.data.responseSheet.finishedTime,
                }
                responseSheetDTO.qnnreDTO = {
                    qnnre: res.data.qnnreDTO.qnnre,
                    questionDTOList: [{
                        question: {
                            id,
                            qnnreId,
                            content,
                            required,
                            type,
                        },
                        optionList: [{
                            option: {
                                id,
                                questionId,
                                content,
                                qnnreId,
                            },
                            selected
                        }],
                    }] = res.data.qnnreDTO.questionDTOList
                }
                questionnaireId = responseSheetDTO.qnnreDTO.qnnre.id
                questionnaireTitle = responseSheetDTO.qnnreDTO.qnnre.name
                questionnaireDescription = responseSheetDTO.qnnreDTO.qnnre.description
                $('#qnnreTitle').text(questionnaireTitle)
                $('#qnnreDescription').text(questionnaireDescription)
                $('#respondentName').text(responseSheetDTO.responseSheet.respondentName)
                load()
            } else {
                alert(res.message)
            }
        }
    });
}

const load = () => {
    responseSheetDTO.qnnreDTO.questionDTOList.forEach(questionDTO => {
        switch (questionDTO.question.type) {
            case 'SINGLE_CHOICE_QUESTION':
                singleChoice(questionDTO)
                break;
            case 'MULTIPLE_CHOICE_QUESTION':
                singleM(questionDTO)
                break;
            default:
                break;
        }
    })
}

/**
 * 增加一个单选题(已作答)
 * @param questionDTO
 * @author AkagawaTsurunaki
 */
const singleChoice = (questionDTO) => {
    let question = questionDTO.question
    let ele = `
    <div class="question" id="question${question.id}" data-type="1" data-problemIndex="${question.id}">
        <div class="top">
            <span class="question-title" id="questionTitle">${question.content}</span>
            <span class="must-answer" id="mustAnswer">${question.required === 'REQUIRED' ? '必答题' : '非必答题'}</span>
        </div>
        <div class="bottom2" style="display: inline;">
        
        </div>
    </div>
    `
    $('#problem').append(ele)
    questionDTO.optionList.map(optionDTO => {
    $(`#question${question.id} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio" ${optionDTO.selected ? 'checked' : ''} disabled>${optionDTO.option.content}
        </label>
      </div>
    `)
    })
}
